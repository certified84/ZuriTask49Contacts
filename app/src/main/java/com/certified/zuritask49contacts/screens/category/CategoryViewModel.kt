package com.certified.zuritask49contacts.screens.category

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certified.zuritask49contacts.Category
import kotlinx.coroutines.launch

class CategoryViewModel(private val categoryList: List<Category>) : ViewModel() {

    private val _categories = MutableLiveData<List<Category>>()
    val categories: LiveData<List<Category>>
        get() = _categories

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            try {
                _categories.value = categoryList

            } catch (e: Exception) {
                Log.e("ViewModel", "getCategories: An error occurred. ${e.message}")
            }
        }
    }
}