package app.sosocom.smallstep.presentation.ui.todo

import android.content.Context
import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.base.BaseBottomSheetDialog
import app.sosocom.smallstep.presentation.databinding.DialogTodoEditBinding

class TodoEditDialog(context: Context) : BaseBottomSheetDialog<DialogTodoEditBinding>(context, R.layout.dialog_todo_edit) {
    var todo: Todo? = null
    set(value) {
        field = value
        binding.editContent.setText(value?.content)
        binding.editContent.setSelection(value?.content?.length ?: 0)
    }

    fun setOnSaveListener(listener: (Todo?, String) -> Unit) {
        binding.send.setOnClickListener {
            listener(todo, (binding.editContent.text ?: "").toString())

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

        todo = null
    }
}