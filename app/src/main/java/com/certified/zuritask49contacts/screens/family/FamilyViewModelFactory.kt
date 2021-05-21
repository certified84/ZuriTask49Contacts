package com.certified.zuritask49contacts.screens.family

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask49contacts.room.ContactsDao

@Suppress("UNCHECKED_CAST")
class FamilyViewModelFactory(
    private val database: ContactsDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FamilyViewModel::class.java)) {
            return FamilyViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}