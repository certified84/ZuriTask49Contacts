package com.certified.zuritask49contacts.screens.colleagues

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask49contacts.Contact

@Suppress("UNCHECKED_CAST")
class ColleaguesViewModelFactory(private val contactList: List<Contact>): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ColleaguesViewModel::class.java)) {
            return ColleaguesViewModel(contactList) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}