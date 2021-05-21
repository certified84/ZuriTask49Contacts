package com.certified.zuritask49contacts.screens.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.certified.zuritask49contacts.room.ContactsDao
import com.certified.zuritask49contacts.room.Credential
import kotlinx.coroutines.launch

class LoginViewModel(
    private val database: ContactsDao,
    application: Application
) : AndroidViewModel(application) {

    val credentials = database.getCredentials()

//    private val _credentials = MutableLiveData<Credential>()
//    val credential: LiveData<Credential>
//        get() = _credentials
//
//    fun getCredentials() {
//        viewModelScope.launch {
//            _credentials.value = database.getCredentials()
//        }
//    }
}