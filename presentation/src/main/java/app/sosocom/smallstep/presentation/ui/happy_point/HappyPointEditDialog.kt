package app.sosocom.smallstep.presentation.ui.happy_point

import android.content.Context
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseBottomSheetDialog
import app.sosocom.smallstep.presentation.databinding.DialogHappyPointEditBinding
import java.time.LocalDate
import java.time.LocalDateTime

class HappyPointEditDialog(context: Context, val baseDate: LocalDate) : BaseBottomSheetDialog<DialogHappyPointEditBinding>(context, R.layout.dialog_happy_point_edit) {
    var happyPoint: HappyPoint? = null
        set(value) {
            field = value
            binding.editContent.setText(value?.content)
            binding.editComment.setText(value?.comment)
            binding.seekbarPoint.progress = value?.point?.times(2)?.toInt() ?: 0
        }

    fun setOnDoneListener(listener: (HappyPoint) -> Unit) {
        binding.btnDone.setOnClickListener {
            val editContent = binding.editContent.text
            if(editContent.isNullOrEmpty()) return@setOnClickListener

            val happyPoint = happyPoint?.apply {
                content = editContent.toString()
                comment = binding.editComment.text.toString()
                point = binding.seekbarPoint.progress.div(2f)
            } ?: HappyPoint(
                content = editContent.toString(),
                comment = binding.editComment.text.toString(),
                point = binding.seekbarPoint.progress.div(2f),
                baseDate = baseDate,
                createdAt = LocalDateTime.now()
            )

            listener(happyPoint)

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