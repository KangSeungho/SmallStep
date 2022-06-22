package app.sosocom.smallstep.presentation.ui.main.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import app.sosocom.smallstep.domain.model.DailyWriteBundle
import app.sosocom.smallstep.presentation.databinding.ItemCalendarDayBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.LocalDate

class CustomDayBinder : DayBinder<CustomDayBinder.DayViewContainer> {
    private var dataMap = emptyMap<Int, DailyWriteBundle>()
    fun setDataMap(data: Map<Int, DailyWriteBundle>) {
        dataMap = data
    }

    var selectedDate: LocalDate? = null
    set(value) {
        val before = field
        field = value

        if (value != null) {
            onDayClickListener?.invoke(before, value)
        }
    }

    private var onDayClickListener: ((LocalDate?, LocalDate) -> Unit)? = null
    fun setOnDayClickListener(listener: (LocalDate?, LocalDate) -> Unit) {
        onDayClickListener = listener
    }

    override fun create(view: View) = DayViewContainer(view)

    override fun bind(container: DayViewContainer, day: CalendarDay) {
        container.bind(day)
    }

    inner class DayViewContainer(view: View) : ViewContainer(view) {
        private val binding = DataBindingUtil.bind<ItemCalendarDayBinding>(view)

        fun bind(day: CalendarDay) {
            binding ?: return

            binding.item = if(day.owner == DayOwner.THIS_MONTH) dataMap[day.day] else null
            binding.isThisMonth = (day.owner == DayOwner.THIS_MONTH)
            binding.isSelected = (day.date == selectedDate)

            binding.textDay.text = day.date.dayOfMonth.toString()

            // 클릭
            binding.root.setOnClickListener {
                if(day.owner == DayOwner.THIS_MONTH) {
                    selectedDate = day.date
                }
            }
        }
    }
}