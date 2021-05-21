package com.certified.zuritask49contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.certified.zuritask49contacts.databinding.ListItemCategoryBinding
import com.certified.zuritask49contacts.databinding.ListItemContactBinding
import com.certified.zuritask49contacts.room.Contact

class CategoryAdapter(val categories: List<Category>) : ListAdapter<Category, CategoryAdapter.ViewHolder>(diffCallback) {

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<Category>() {
            override fun areItemsTheSame(oldItem: Category, newItem: Category) = false

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
    }
}