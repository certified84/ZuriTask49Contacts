package com.certified.zuritask49contacts.screens.colleagues

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ColleaguesViewModel(
    private val database: ContactsDao,
    application: Application) : AndroidViewModel(application) {

    val contacts = database.getContactsByCategory("Colleagues")

    private suspend fun updateContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            database.updateContact(contact)
        }
    }

    private suspend fun deleteContact(contact: Contact) {
        withContext(Dispatchers.IO) {
            database.deleteContact(contact)
        }
    }
}