package com.certified.zuritask49contacts

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactsDao
import kotlinx.coroutines.launch

class MainActivityViewModel(
    private val database: ContactsDao,
    application: Application
) : AndroidViewModel(application) {

    fun insertContact(contact: Contact) {
        viewModelScope.launch {
            database.insertContact(contact)
        }
    }
}