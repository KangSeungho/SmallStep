package app.sosocom.smallstep.presentation.ui.happy_point.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import app.sosocom.smallstep.domain.model.HappyPoint
import app.sosocom.smallstep.presentation.R
import app.sosocom.smallstep.presentation.databinding.ItemHappyPointBinding

class HappyPointAdapter: ListAdapter<HappyPoint, HappyPointAdapter.HappyPointViewHolder>(diff) {
    companion object {
        val diff = object : DiffUtil.ItemCallback<HappyPoint>() {
            override fun areItemsTheSame(oldItem: HappyPoint, newItem: HappyPoint) = (oldItem.id == newItem.id)

            override fun areContentsTheSame(oldItem: HappyPoint, newItem: HappyPoint) = (oldItem == newItem)
        }
    }

    private var onItemClickListener: ((HappyPoint) -> Unit)? = null

    fun setOnItemClickListener(listener: (HappyPoint) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HappyPointViewHolder {
        return HappyPointViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_happy_point, parent, false))
    }

    override fun onBindViewHolder(holder: HappyPointViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HappyPointViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val binding = DataBindingUtil.bind<ItemHappyPointBinding>(view)

        fun bind(happyPoint: HappyPoint) {
            binding ?: return

            binding.item = happyPoint

            binding.root.setOnClickListener { onItemClickListener?.invoke(happyPoint) }
        }
    }
}