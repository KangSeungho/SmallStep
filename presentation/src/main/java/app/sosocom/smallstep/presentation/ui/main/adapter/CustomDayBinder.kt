package app.sosocom.smallstep.presentation.ui.main.adapter

import android.view.View
import androidx.databinding.DataBindingUtil
import app.sosocom.smallstep.domain.model.DayWrites
import app.sosocom.smallstep.presentation.databinding.ItemCalendarDayBinding
import com.kizitonwose.calendarview.model.CalendarDay
import com.kizitonwose.calendarview.model.DayOwner
import com.kizitonwose.calendarview.ui.DayBinder
import com.kizitonwose.calendarview.ui.ViewContainer

class CustomDayBinder : DayBinder<CustomDayBinder.DayViewContainer> {
    private var dataMap = emptyMap<Int, DayWrites>()
    fun setDataMap(data: Map<Int, DayWrites>) {
        dataMap = data
    }

    private var selectedDate: CalendarDay? = null
    fun setSelectedDate(date: CalendarDay) {
        selectedDate = date
    }

    private var onDayClickListener: ((CalendarDay) -> Unit)? = null
    fun setOnDayClickListener(listener: (CalendarDay) -> Unit) {
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
            binding.isSelected = (day == selectedDate)

            binding.textDay.text = day.date.dayOfMonth.toString()

            // 클릭
            binding.root.setOnClickListener {
                if(day.owner == DayOwner.THIS_MONTH) {
                    setSelectedDate(day)
                    onDayClickListener?.invoke(day)
                }
            }
        }
    }
}