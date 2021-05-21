package com.certified.zuritask49contacts.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "credential_table")
data class Credential(

    val email: String,

    val password: String
) {
    @PrimaryKey
    var id: Int = 0
}