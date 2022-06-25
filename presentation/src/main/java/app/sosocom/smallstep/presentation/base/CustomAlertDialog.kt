package app.sosocom.smallstep.presentation.base

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Window
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatDialog
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.DialogCustomAlertBinding

class CustomAlertDialog(context: Context): AppCompatDialog(context) {
    private lateinit var binding: DialogCustomAlertBinding

    init {
        // 배경 색상 변경
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 타이틀 바 제거
        requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 기본 취소 X
        setCancelable(false)

        // 생성
        create()

        // 버튼 기본 이벤트
        binding.tvOk.setOnClickListener { dismiss() }
        binding.tvCancel.setOnClickListener { dismiss() }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_custom_alert, null, false)
        setContentView(binding.root)
    }

    fun setTitle(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setTitle(context.getString(strRes, formatArgs))

    fun setTitle(title: String) {
        binding.tvTitle.isVisible = title.isNotEmpty()
        binding.tvTitle.text = title
    }

    fun setMessage(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setMessage(context.getString(strRes, formatArgs))

    fun setMessage(message: String) {
        binding.tvMessage.text = message
    }

    fun isCancel(isCancel: Boolean) {
        binding.tvCancel.isVisible = isCancel
        binding.dividerCancel.isVisible = isCancel
    }

    fun setCancelText(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setCancelText(context.getString(strRes, formatArgs))

    fun setCancelText(text: String) {
        binding.tvCancel.text = text
    }

    fun setOnCancelListener(listener: () -> Unit) {
        binding.tvCancel.setOnClickListener {
            dismiss()
            listener()
        }
    }

    fun setOkText(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setOkText(context.getString(strRes, formatArgs))

    fun setOkText(text: String) {
        binding.tvOk.text = text
    }

    fun setOnClickListener(listener: () -> Unit) {
        binding.tvOk.setOnClickListener {
            dismiss()
            listener()
        }
    }
}