package com.certified.zuritask49contacts.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact(
    @field:ColumnInfo(name = "contact_name") val name: String,
    @field:ColumnInfo(name = "contact_number") val number: String,
    @field:ColumnInfo(name = "contact_category") val category: String
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}