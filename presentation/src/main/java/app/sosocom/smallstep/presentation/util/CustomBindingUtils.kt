package app.sosocom.smallstep.presentation.util

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import java.text.SimpleDateFormat
import java.util.*

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(visible: Boolean?) {
    isVisible = visible ?: false
}

@BindingAdapter("setFormatterDateText")
fun setFormatterDateText(view: TextView, calendar: Calendar?) {
    calendar ?: return

    view.text = SimpleDateFormat("YYYY년 MM월 dd일 E요일", Locale.KOREA).format(calendar.time)
}