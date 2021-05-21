package com.certified.zuritask49contacts.screens.friends

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.certified.zuritask49contacts.room.Contact
import com.certified.zuritask49contacts.room.ContactsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FriendsViewModel(
    private val database: ContactsDao,
    application: Application
) : AndroidViewModel(application) {

    val contacts = database.getContactsByCategory("Friends")

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