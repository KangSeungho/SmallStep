package app.sosocom.smallstep.presentation.base

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.DialogCustomAlertBinding

class CustomAlertDialog(val context: Context) {
    private val dlg = Dialog(context)
    private lateinit var binding: DialogCustomAlertBinding

    private var title: String? = null
    private var message: String? = null

    private var isCancel = false
    private var cancelText = context.getString(R.string.cancel)
    private var onCancelListener: (() -> Unit)? = null

    private var okText = context.getString(R.string.ok)
    private var onOkListener: (() -> Unit)? = null

    fun setTitle(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setTitle(context.getString(strRes, formatArgs))

    fun setTitle(title: String): CustomAlertDialog {
        this.title = title

        return this
    }

    fun setMessage(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setMessage(context.getString(strRes, formatArgs))

    fun setMessage(message: String): CustomAlertDialog {
        this.message = message

        return this
    }

    fun isCancel(isCancel: Boolean): CustomAlertDialog {
        this.isCancel = isCancel

        return this
    }

    fun setCancelText(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setCancelText(context.getString(strRes, formatArgs))

    fun setCancelText(text: String): CustomAlertDialog {
        this.cancelText = text

        return this
    }

    fun setOnCancelListener(listener: () -> Unit): CustomAlertDialog {
        this.onCancelListener = listener

        return this
    }

    fun setOkText(@StringRes strRes: Int, vararg formatArgs: Any?) =
        setOkText(context.getString(strRes, formatArgs))

    fun setOkText(text: String): CustomAlertDialog {
        this.okText = text

        return this
    }

    fun setOnClickListener(listener: () -> Unit): CustomAlertDialog {
        this.onOkListener = listener

        return this
    }

    fun show() {
        // 배경 색상 변경
        dlg.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // 타이틀바 제거
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE)

        // 데이터 바인딩
        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_custom_alert, null, false)
        dlg.setContentView(binding.root)

        // 제목
        if(title != null) {
            binding.tvTitle.visibility = View.VISIBLE
            binding.tvTitle.text = title
        }

        // 내용
        if(message != null) {
            binding.tvMessage.text = message
        }

        // 취소
        when(isCancel) {
            // 표시
            true -> {
                binding.tvCancel.text = cancelText
                binding.tvCancel.setOnClickListener {
                    dlg.dismiss()
                    onCancelListener?.invoke()
                }
            }

            // 숨기기
            false -> {
                binding.tvCancel.visibility = View.GONE
                binding.dividerCancel.visibility = View.GONE
            }
        }

        // 확인
        binding.tvOk.text = okText
        binding.tvOk.setOnClickListener {
            dlg.dismiss()
            onOkListener?.invoke()
        }

        // 호출한 Context가 종료되지 않았을 때만 표시
        if(context is Activity && !context.isFinishing)
            dlg.show()
    }
}