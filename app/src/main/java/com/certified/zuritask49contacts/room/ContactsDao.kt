package com.certified.zuritask49contacts.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface ContactsDao {

    @Insert
    suspend fun insertContact(contact: Contact)

    @Insert
    suspend fun insertCredential(credential: Credential)

    @Update
    suspend fun updateCredential(credential: Credential)

    @Query("SELECT * FROM contacts_table ORDER BY contact_name DESC")
    fun getContacts(): LiveData<List<Contact>>

    @Query("SELECT * FROM credential_table WHERE id = 0")
    fun getCredentials(): LiveData<Credential>
}