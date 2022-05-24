package app.sosocom.smallstep.ui

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityMainBinding
import app.sosocom.smallstep.ui.emotion_diary.DiaryActivity
import app.sosocom.smallstep.ui.emotion_diary.DiaryEditActivity
import app.sosocom.smallstep.util.ExtraConstants
import com.applandeo.materialcalendarview.EventDay
import dagger.hilt.android.AndroidEntryPoint
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

//        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getMonthWrites(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH))
//        }
    }

    private fun initUI() {
        // 날짜 선택
        binding.calendarView.setOnDayClickListener { eventDay ->
            viewModel.setSelDay(eventDay.calendar.get(Calendar.DAY_OF_MONTH))
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
        }
    }
    fun onClickTodo(view: View) {

    }

    fun onClickHappyPoint(view: View) {

    }

    fun onClickGoodAndNew(view: View) {

    }

    /**
     * 일기 항목 클릭
     */
    fun onClickEmotionDiary(view: View) {
        val diary = viewModel.selDayWrites.value?.diary ?: return

        val intent = Intent(this, DiaryActivity::class.java)
        intent.putExtra(ExtraConstants.EXTRA_DIARY, diary)
        startActivity(intent)
    }

    /**
     * 추가 버튼 클릭
     */
    fun onClickFloatingButton(view: View) {
        val isSelected = !view.isSelected
        view.isSelected = isSelected

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

    /**
     * 일기 추가 버튼 클릭
     */
    fun onClickAddDiary(view: View) {
        val intent = Intent(this, DiaryEditActivity::class.java)
        startActivity(intent)
    }
}