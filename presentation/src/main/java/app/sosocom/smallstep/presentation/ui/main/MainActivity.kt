package app.sosocom.smallstep.presentation.ui.main

import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import app.sosocom.smallstep.domain.model.DailyTodoBundle
import app.sosocom.smallstep.presentation.base.BaseActivity
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.CustomAlertDialog
import app.sosocom.smallstep.presentation.databinding.ActivityMainBinding
import app.sosocom.smallstep.presentation.ui.diary.DiaryActivity
import app.sosocom.smallstep.presentation.ui.diary.DiaryEditActivity
import app.sosocom.smallstep.presentation.ui.happy_point.HappyPointListActivity
import app.sosocom.smallstep.presentation.ui.main.adapter.CustomDayBinder
import app.sosocom.smallstep.presentation.ui.main.adapter.CustomMonthHeaderBinder
import app.sosocom.smallstep.presentation.ui.todo.TodoListActivity
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
        // ?????? ??? ??????
        binding.btnNextMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.next)
            }
        }

        // ?????? ??? ??????
        binding.btnPrevMonth.setOnClickListener {
            binding.calendarView.findFirstVisibleMonth()?.let {
                binding.calendarView.smoothScrollToMonth(it.yearMonth.previous)
            }
        }

        binding.calendarView.run {
            // ?????? ??? ??????
            val daysOfWeek = daysOfWeekFromLocale()
            val currentMonth = YearMonth.now()
            setup(currentMonth.minusMonths(10), currentMonth.plusMonths(10), daysOfWeek.first())
            scrollToMonth(currentMonth)

            // ??? ??????
            monthScrollListener = { month ->
                val title = "${month.year}??? ${month.month}???"
                binding.textCurrentMonth.text = title

                // ?????? ????????? ????????? ?????? ?????? ????????? ????????? ????????? 1?????? ?????? ??????
                if(customDayBinder.selectedDate != null) {
                    customDayBinder.selectedDate = LocalDate.of(month.year, month.month, 1)
                }

                // ????????? ?????????
                customDayBinder.setDataMap(mutableMapOf())
                notifyMonthChanged(month.yearMonth)

                // ????????? ??????
                CoroutineScope(Dispatchers.IO).launch {
                    viewModel.getMonthWrites(month.year, month.month)
                }
            }

            // ??? ??????
            monthHeaderBinder = CustomMonthHeaderBinder()

            // ??? ??????
            dayBinder = customDayBinder

            // ?????? ??????
            customDayBinder.setOnDayClickListener { beforeDate, afterDate ->
                if (beforeDate != null) {
                    notifyDateChanged(beforeDate)
                }
                notifyDateChanged(afterDate)

                viewModel.setSelectedDate(afterDate)
            }
        }

        // ???????????? ??????
        binding.cardHappyPoint.setOnClickListener {
            val happyPointBundle = viewModel.selDailyWriteBundle.value?.dailyHappyPointBundle ?: return@setOnClickListener

            val intent = Intent(this, HappyPointListActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DAILY_HAPPY_POINT_BUNDLE, happyPointBundle)
            startActivity(intent)
        }

        // ?????? ?????? ??????
        binding.cardTodo.setOnClickListener {
            val todoBundle = viewModel.selDailyWriteBundle.value?.dailyTodoBundle ?: return@setOnClickListener

            val intent = Intent(this, TodoListActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DAILY_TODO_BUNDLE, todoBundle)
            startActivity(intent)
        }

        // ?????? ?????? ??????
        binding.cardEmotionDiary.setOnClickListener {
            val diary = viewModel.selDailyWriteBundle.value?.diary ?: return@setOnClickListener

            val intent = Intent(this, DiaryActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DIARY, diary)
            startActivity(intent)
        }

        // ?????? ????????? ?????? ??????
        binding.floatingActionButton.setOnClickListener {
            val isSelected = !it.isSelected
            it.isSelected = isSelected

            when(isSelected) {
                // ?????? ?????? ??????
                true -> {
                    ObjectAnimator.ofFloat(binding.btnAddHappyPoint, "translationY", -540f).apply { start() }
                    ObjectAnimator.ofFloat(binding.btnAddTodo, "translationY", -360f).apply { start() }
                    ObjectAnimator.ofFloat(binding.btnAddDiary, "translationY", -180f).apply { start() }
                    ObjectAnimator.ofFloat(binding.floatingActionButton, View.ROTATION, 0f, 45f).apply { start() }
                }

                // ?????? ?????? ??????
                false -> {
                    ObjectAnimator.ofFloat(binding.btnAddHappyPoint, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.btnAddTodo, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.btnAddDiary, "translationY", 0f).apply { start() }
                    ObjectAnimator.ofFloat(binding.floatingActionButton, View.ROTATION, 45f, 0f).apply { start() }
                }
            }
        }

        // ?????? ?????? ??????
        binding.btnAddTodo.setOnClickListener {
            // ?????? ????????? ?????? ?????? ??????
            binding.floatingActionButton.performClick()

            val selectedDate = customDayBinder.selectedDate ?: LocalDate.now()

            val todoBundle = viewModel.selDailyWriteBundle.value?.dailyTodoBundle ?: DailyTodoBundle(ArrayList(), selectedDate)

            val intent = Intent(this, TodoListActivity::class.java)
            intent.putExtra(ExtraConstants.EXTRA_DAILY_TODO_BUNDLE, todoBundle)
            startActivity(intent)
            pushActivity()
        }

        // ?????? ?????? ??????
        binding.btnAddDiary.setOnClickListener {
            // ?????? ????????? ?????? ?????? ??????
            binding.floatingActionButton.performClick()

            // ????????? ?????? ????????? ?????? ??? ??? ??????
            if(LocalDate.now() < customDayBinder.selectedDate) {
                CustomAlertDialog(this).apply {
                    setMessage(R.string.diary_cannot_write)
                    show()
                }

                return@setOnClickListener
            }

            val diary = viewModel.selDailyWriteBundle.value?.diary

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
            val calendarMonth = binding.calendarView.findFirstVisibleMonth() ?: return@observe

            // ????????? ??????
            customDayBinder.setDataMap(dataMap)

            // ??????
            binding.calendarView.notifyMonthChanged(calendarMonth.yearMonth)

            // ????????? ?????? ????????? ??????
            when(val selectedDate = customDayBinder.selectedDate) {
                // ????????? ????????? ????????? ?????? ????????? ???????????? ??????
                null -> {
                    viewModel.setSelectedDate(LocalDate.now())
                    customDayBinder.selectedDate = LocalDate.now()
                }

                // ?????? ?????? ???????????? ????????? ???????????? ??????
                else -> {
                    viewModel.setSelectedDate(selectedDate)
                }
            }
        }
    }
}