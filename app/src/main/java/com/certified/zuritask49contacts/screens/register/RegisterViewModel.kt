package com.certified.zuritask49contacts.screens.register

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.certified.zuritask49contacts.room.ContactsDao
import com.certified.zuritask49contacts.room.Credential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterViewModel(
    private val database: ContactsDao,
    application: Application
) : AndroidViewModel(application) {

    fun register(credentials: Credential) {
        viewModelScope.launch {
            database.updateCredential(credentials)
        }
    }
}