package com.certified.zuritask49contacts

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.screens.category.CategoryAdapter

@BindingAdapter("contactList")
fun bindContactRecyclerView(recyclerView: RecyclerView, data: List<Contact>?) {
    val adapter = recyclerView.adapter as ContactAdapter
    adapter.submitList(data)
}

@BindingAdapter("categories")
fun bindCategoryRecyclerView(recyclerView: RecyclerView, data: List<Category>?) {
    val adapter = recyclerView.adapter as CategoryAdapter
    adapter.submitList(data)
}

@BindingAdapter("categoryImage")
fun bindImage(imgView: ImageView, categoryImage: Int) {
    categoryImage.let {
        Glide.with(imgView.context)
            .load(categoryImage)
            .centerCrop()
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(imgView)
    }
}