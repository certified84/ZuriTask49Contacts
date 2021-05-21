package com.certified.zuritask49contacts.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.certified.zuritask49contacts.room.ContactsDao

class LoginViewModel(
    database: ContactsDao,
    application: Application
) : AndroidViewModel(application) {

    val credentials = database.getCredentials()
}