package com.certified.zuritask49contacts

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.certified.zuritask49contacts.room.Contact

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Contact>?) {
    val adapter = recyclerView.adapter as ContactAdapter
    adapter.submitList(data)
}