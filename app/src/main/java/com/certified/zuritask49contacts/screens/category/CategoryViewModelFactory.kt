package com.certified.zuritask49contacts.screens.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask49contacts.Category

@Suppress("UNCHECKED_CAST")
class CategoryViewModelFactory(private val categories: List<Category>) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryViewModel::class.java)) {
            return CategoryViewModel(categories) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}