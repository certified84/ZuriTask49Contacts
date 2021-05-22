package com.certified.zuritask49contacts.screens.tutors

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.certified.zuritask49contacts.room.ContactsDao

@Suppress("UNCHECKED_CAST")
class TutorsViewModelFactory(
    private val database: ContactsDao,
    private val application: Application
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TutorsViewModel::class.java)) {
            return TutorsViewModel(database, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}