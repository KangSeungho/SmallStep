package app.sosocom.smallstep.presentation.util

import android.view.View
import androidx.core.view.isVisible
import androidx.databinding.BindingAdapter

@BindingAdapter("android:visibleIf")
fun View.setVisibleIf(visible: Boolean?) {
    isVisible = visible ?: false
}

@BindingAdapter("android:goneIf")
fun View.setGoneIf(isGone: Boolean?) {
    isVisible = !(isGone ?: false)
}