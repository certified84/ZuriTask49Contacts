package com.certified.zuritask49contacts.screens.category

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.certified.zuritask49contacts.Category
import com.certified.zuritask49contacts.databinding.ListItemCategoryBinding

class CategoryAdapter() : ListAdapter<Category, CategoryAdapter.ViewHolder>(diffCallback) {

    private lateinit var listener: OnCategoryClickedListener

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: Category,
                newItem: Category
            ): Boolean = oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            ListItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = getItem(position)

        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    inner class ViewHolder(private val binding: ListItemCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(category: Category) {
            binding.category = category
            binding.executePendingBindings()
        }

        init {
            (binding.root).setOnClickListener{
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    listener.onClick(getItem(position))
                }
            }
        }
    }

    fun setOnCategoryClickedListener(listener: OnCategoryClickedListener) {
        this.listener = listener
    }

    interface OnCategoryClickedListener {
        fun onClick(category: Category)
    }
}

class CategoryClickListener(val clickListener: (id: Int) -> Unit) {
    fun onClick(category: Category) = clickListener(category.id)
}