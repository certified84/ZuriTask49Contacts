package com.certified.zuritask49contacts.screens.colleagues

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.certified.zuritask49contacts.Contact
import kotlinx.coroutines.launch

class ColleaguesViewModel(private val contactList: List<Contact>) : ViewModel(){

    private val _contacts = MutableLiveData<List<Contact>>()
    val contacts: LiveData<List<Contact>>
        get() = _contacts

    init {
        getCharacters()
    }

    private fun getCharacters() {
        viewModelScope.launch {
            try {
                _contacts.value = contactList

            } catch (e: Exception) {
                //                Toast.makeText(coroutineContext, "An error occurred", Toast.LENGTH_LONG).show()
                Log.e("ViewModel", "getCharacters: An error occurred. ${e.message}")
            }
        }
    }
}