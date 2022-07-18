package app.sosocom.smallstep.presentation.ui.happy_point

import android.content.Context
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseBottomSheetDialog
import app.sosocom.smallstep.presentation.databinding.DialogHappyPointEditBinding

class HappyPointEditDialog(context: Context) : BaseBottomSheetDialog<DialogHappyPointEditBinding>(context, R.layout.dialog_happy_point_edit) {
    var happyPoint: HappyPoint? = null
        set(value) {
            field = value
            binding.editContent.setText(value?.content)
            binding.editComment.setText(value?.comment)
            binding.seekbarPoint.progress = value?.point?.times(2)?.toInt() ?: 0
        }

    fun setOnDoneListener(listener: (HappyPoint?, String, String, Float) -> Unit) {
        binding.btnDone.setOnClickListener {
            listener(
                happyPoint,
                (binding.editContent.text ?: "").toString(),
                (binding.editComment.text ?: "").toString(),
                binding.seekbarPoint.progress.div(2f)
            )

            dismiss()
        }
    }

    override fun show() {
        super.show()

        // 입력창에 포커스
        binding.editContent.requestFocus()
    }

    override fun dismiss() {
        super.dismiss()

        happyPoint = null
    }
}