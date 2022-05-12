package app.sosocom.smallstep.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import app.sosocom.smallstep.R
import app.sosocom.smallstep.base.BaseActivity
import app.sosocom.smallstep.databinding.ActivityMainBinding
import app.sosocom.smallstep.ui.emotion_diary.EmotionDiaryActivity
import com.applandeo.materialcalendarview.EventDay
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.*

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getLayoutResId() = R.layout.activity_main
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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

    }

    private fun initObserver() {
        viewModel.monthWrites.observe(this) {
            it ?: return@observe

            val calendar = binding.calendarView.currentPageDate.clone() as Calendar
            val eventsList = LinkedList<EventDay>()
            for(dayWrites in it) {
                val day = calendar.clone() as Calendar
                day.set(Calendar.DAY_OF_MONTH, dayWrites.day)

                // 투두 이벤트 추가
                if(dayWrites.todoList != null)
                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_todo))

                // HappyPoint 이벤트 추가
                if(dayWrites.happyPointList != null)
                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_happy_point))

                // GoodAndNew 이벤트 추가
                if(dayWrites.goodAndNew != null)
                    eventsList.add(EventDay(day.clone() as Calendar, R.drawable.icon_calendar_good_and_new))

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

    fun onClickEmotionDiary(view: View) {
        val intent = Intent(this, EmotionDiaryActivity::class.java)
        startActivity(intent)
    }
}