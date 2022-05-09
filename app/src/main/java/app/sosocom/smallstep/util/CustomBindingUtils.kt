package app.sosocom.smallstep.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(visible: Boolean?) {
    isVisible = visible ?: false
}