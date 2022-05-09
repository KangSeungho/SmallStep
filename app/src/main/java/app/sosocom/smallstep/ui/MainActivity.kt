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

        CoroutineScope(Dispatchers.IO).launch {
            viewModel.getMonthWrites(selectedDate.get(Calendar.YEAR), selectedDate.get(Calendar.MONTH))
        }
    }

    private fun initUI() {

    }

    private fun initObserver() {
        viewModel.monthWrites.observe(this) {
            it ?: return@observe

            val eventsList = LinkedList<EventDay>()
            for(dayWrites in it) {
                // TODO : 날짜 별 EventDay 추가 요망
            }
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