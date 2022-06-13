package app.sosocom.smallstep.presentation.ui.main.adapter

import android.view.View
import android.widget.TextView
import androidx.core.view.children
import androidx.databinding.DataBindingUtil
import app.sosocom.smallstep.presentation.databinding.IncludeCalendarHeaderBinding
import app.sosocom.smallstep.presentation.util.daysOfWeekFromLocale
import com.kizitonwose.calendarview.model.CalendarMonth
import com.kizitonwose.calendarview.ui.MonthHeaderFooterBinder
import com.kizitonwose.calendarview.ui.ViewContainer
import java.time.format.TextStyle
import java.util.*

class CustomMonthHeaderBinder : MonthHeaderFooterBinder<CustomMonthHeaderBinder.MonthViewContainer> {

    private val daysOfWeek = daysOfWeekFromLocale()

    override fun create(view: View) = MonthViewContainer(view)

    override fun bind(container: MonthViewContainer, month: CalendarMonth) {
        container.binding ?: return

        // Setup each header day text if we have not done that already.
        if (container.binding.root.tag == null) {
            container.binding.root.tag = month.yearMonth
            container.binding.headerLayout.children.map { it as TextView }.forEachIndexed { index, tv ->
                tv.text = daysOfWeek[index].getDisplayName(TextStyle.SHORT, Locale.KOREA)
            }
            month.yearMonth
        }
    }

    inner class MonthViewContainer(view: View) : ViewContainer(view) {
        val binding = DataBindingUtil.bind<IncludeCalendarHeaderBinding>(view)
    }
}