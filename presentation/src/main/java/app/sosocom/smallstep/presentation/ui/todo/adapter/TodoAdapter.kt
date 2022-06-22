package app.sosocom.smallstep.presentation.ui.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sosocom.smallstep.domain.model.Todo
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.ItemTodoBinding

class TodoAdapter: ListAdapter<Todo, TodoAdapter.TodoViewHolder>(diff) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<Todo>() {
            override fun areItemsTheSame(oldItem: Todo, newItem: Todo) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: Todo, newItem: Todo) = (oldItem == newItem)
        }
    }

    private var onItemClickListener: ((Todo) -> Unit)? = null
    private var onCheckedListener: ((Todo, Boolean) -> Unit)? = null

    fun setOnItemClickListener(listener: (Todo) -> Unit) {
        onItemClickListener = listener
    }

    fun setOnCheckedListener(listener: (Todo, Boolean) -> Unit) {
        onCheckedListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoAdapter.TodoViewHolder {
        return TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))
    }

    override fun onBindViewHolder(holder: TodoAdapter.TodoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class TodoViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = DataBindingUtil.bind<ItemTodoBinding>(view)

        fun bind(todo: Todo) {
            binding ?: return

            binding.item = todo

            binding.root.setOnClickListener { onItemClickListener?.invoke(todo) }

            binding.checkComplete.setOnClickListener {
                val isChecked = binding.checkComplete.isChecked

                onCheckedListener?.invoke(todo, isChecked)
            }
        }
    }
}