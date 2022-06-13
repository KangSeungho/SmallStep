package app.sosocom.smallstep.presentation.util

import android.view.View
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(visible: Boolean?) {
    isVisible = visible ?: false
}

@BindingAdapter("setFormatterDateText")
fun setFormatterDateText(view: TextView, localDate: LocalDate?) {
    localDate ?: return

    view.text = localDate.format(DateTimeFormatter.ofPattern("YYYY년 MM월 dd일 E요일", Locale.KOREA))
}