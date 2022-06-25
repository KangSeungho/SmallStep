package app.sosocom.smallstep.presentation.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import app.sosocom.smallstep.presentation.R
import com.google.android.material.bottomsheet.BottomSheetDialog

abstract class BaseBottomSheetDialog<T: ViewDataBinding>(context: Context, @LayoutRes private val layoutID: Int) : BottomSheetDialog(context) {
    private var _binding: T? = null
    protected val binding: T get() = _binding!!

    init {
        // 배경 색상 변경
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 생성
        create()
    }

    final override fun create() {
        super.create()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.inflate(LayoutInflater.from(context), layoutID, null, false)
        setContentView(binding.root)
    }
}