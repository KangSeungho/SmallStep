package app.sosocom.smallstep.presentation.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.ActivityMainBinding
import app.sosocom.smallstep.presentation.ui.diary.DiaryActivity
import app.sosocom.smallstep.presentation.ui.diary.DiaryEditActivity
import app.sosocom.smallstep.presentation.util.ExtraConstants
import com.applandeo.materialcalendarview.EventDay
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        loadData()
        initUI()
        initObserver()
    }

    private fun loadData() {
        val selectedDate = binding.calendarView.selectedDates[0]

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getMonthWrites(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH))
        }
    }

    private fun initUI() {
        // 날짜 선택
        binding.calendarView.setOnDayClickListener { eventDay ->
            viewModel.setSelDay(eventDay.calendar.get(Calendar.DAY_OF_MONTH))
        }

        // 일기 항목 클릭
        binding.cardEmotionDiary.setOnClickListener {
            val diary = viewModel.selDayWrites.value?.diary ?: return@setOnClickListener

            val intent = Intent(this, DiaryActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DIARY, diary)
            startActivity(intent)
        }

        // 추가 플로팅 버튼 클릭
        binding.floatingActionButton.setOnClickListener {
            val isSelected = !it.isSelected
            it.isSelected = isSelected

            when(isSelected) {
                // 추가 버튼 선택
                true -> {
                    ObjectAnimator.ofFloat(binding.btnAddDiary, "translationY", -180f).apply { start() }
                    ObjectAnimator.ofFloat(binding.floatingActionButton, View.ROTATION, 0f, 45f).apply { start() }
                }

                // 추가 버튼 해제
                false -> {
                    ObjectAnimator.ofFloat(binding.btnAddDiary, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.floatingActionButton, View.ROTATION, 45f, 0f).apply { start() }
                }
            }
        }

        // 일기 추가 버튼
        binding.btnAddDiary.setOnClickListener {
            // 추가 플로팅 버튼 닫기 처리
            binding.floatingActionButton.performClick()

            val diary = viewModel.selDayWrites.value?.diary

            val intent = Intent(this, DiaryEditActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DIARY, diary)
            startActivity(intent)
        }
    }

    private fun initObserver() {
        viewModel.monthWrites.observe(this) {
            it ?: return@observe

            val calendar = binding.calendarView.currentPageDate.clone() as Calendar
            val eventsList = LinkedList<EventDay>()
            for(dayWrites in it.values) {
                val day = calendar.clone() as Calendar
                day.set(Calendar.DAY_OF_MONTH, dayWrites.day)

//                // 투두 이벤트 추가
//                if(dayWrites.todoList != null)
//                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_todo))
//
//                // HappyPoint 이벤트 추가
//                if(dayWrites.happyPointList != null)
//                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_happy_point))
//
//                // GoodAndNew 이벤트 추가
//                if(dayWrites.goodAndNew != null)
//                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_good_and_new))

                // 감정일기 이벤트 추가
                if(dayWrites.diary != null)
                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_diary))
            }

            binding.calendarView.setEvents(eventsList)

            // 기본으로 선택된 날짜의 정보로 세팅한다.
            val selectedDate = binding.calendarView.selectedDates[0]
            viewModel.setSelDay(selectedDate.get(Calendar.DAY_OF_MONTH))
        }
    }
}