package com.certified.zuritask49contacts.screens.contacts

import android.app.Application
import androidx.lifecycle.*
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ContactsViewModel(
    database: ContactsDao,
    application: Application) : AndroidViewModel(application) {

    val contacts = database.getContacts()
}