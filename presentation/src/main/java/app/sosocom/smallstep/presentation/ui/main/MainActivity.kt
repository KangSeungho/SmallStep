package app.sosocom.smallstep.presentation.ui.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import app.sosocom.smallstep.domain.model.Diary
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.CustomAlertDialog
import app.sosocom.smallstep.presentation.databinding.ActivityMainBinding
import app.sosocom.smallstep.presentation.ui.diary.DiaryActivity
import app.sosocom.smallstep.presentation.ui.diary.DiaryEditActivity
import app.sosocom.smallstep.presentation.ui.main.adapter.CustomDayBinder
import app.sosocom.smallstep.presentation.ui.main.adapter.CustomMonthHeaderBinder
import app.sosocom.smallstep.presentation.util.ExtraConstants
import app.sosocom.smallstep.presentation.util.daysOfWeekFromLocale
import com.kizitonwose.calendarview.utils.next
import com.kizitonwose.calendarview.utils.previous
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.YearMonth

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {
    private val viewModel by viewModels<MainViewModel>()

    private val customDayBinder = CustomDayBinder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.lifecycleOwner = this
        binding.vm = viewModel

        initUI()
        initObserver()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun loadData() {
        binding.calendarView.findFirstVisibleMonth()?.let { month ->
            CoroutineScope(Dispatchers.IO).launch {
                viewModel.getMonthWrites(month.year, month.month)
            }
        }
    }

    private fun initUI() {
        // 다음 달 이동
        binding.btnNextMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.next)
            }
        }

        // 이전 달 이동
        binding.btnPrevMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.previous)
            }
        }

        binding.calendarView.run {
            // 현재 달 세팅
            val daysOfWeek = daysOfWeekFromLocale()
            val currentMonth = YearMonth.now()
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)

            // 달 이동
            monthScrollListener = { month ->
                val title = "${month.year}년 ${month.month}월"
                binding.textCurrentMonth.text = title

                // 데이터 초기화
                customDayBinder.setDataMap(mutableMapOf())
                notifyMonthChanged(month.yearMonth)

                // 데이터 로드
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getMonthWrites(month.year, month.month)
                }
            }

            // 주 세팅
            monthHeaderBinder = CustomMonthHeaderBinder()

            // 일 세팅
            dayBinder = customDayBinder

            // 날짜 선택
            customDayBinder.setOnDayClickListener { beforeDate, afterDate ->
                if (beforeDate != null) {
                    notifyDateChanged(beforeDate)
                }
                notifyDateChanged(afterDate)

                viewModel.setSelectedDate(afterDate)
            }
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

            // 일기는 미래 날짜를 미리 쓸 수 없음
            if(LocalDate.now() < customDayBinder.selectedDate) {
                CustomAlertDialog(this)
                    .setMessage(R.string.diary_cannot_write)
                    .show()
                return@setOnClickListener
            }

            val diary = viewModel.selDayWrites.value?.diary

            val intent = Intent(this, DiaryEditActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DIARY, diary)
            intent.putExtra(ExtraConstants.EXTRA_DATE, customDayBinder.selectedDate)
            startActivity(intent)
            pushActivity()
        }
    }

    private fun initObserver() {
        viewModel.monthWrites.observe(this) { dataMap ->
            dataMap ?: return@observe

            // 데이터 세팅
            customDayBinder.setDataMap(dataMap)

            // 갱신
            binding.calendarView.findFirstVisibleMonth()?.let { month ->
                binding.calendarView.notifyMonthChanged(month.yearMonth)
            }

            // 오늘 날짜로 초기 데이터 세티
            if(viewModel.selDayWrites.value == null) {
                viewModel.setSelectedDate(LocalDate.now())
                customDayBinder.selectedDate = LocalDate.now()
            }
        }
    }
}