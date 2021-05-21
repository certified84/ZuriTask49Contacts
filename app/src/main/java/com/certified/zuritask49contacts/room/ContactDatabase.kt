package com.certified.zuritask49contacts.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@Database(entities = [Contact::class, Credential::class], version = 3, exportSchema = false)
abstract class ContactDatabase : RoomDatabase() {

    abstract val contactsDao: ContactsDao

    companion object {
        @Volatile
        private var INSTANCE: ContactDatabase? = null


        private val roomCallback: Callback = object : Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                val contactsDao = INSTANCE?.contactsDao
                GlobalScope.launch {
                    contactsDao?.insertCredential(Credential("", ""))
                }
            }
        }

        fun getInstance(context: Context): ContactDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ContactDatabase::class.java,
                        "contacts_database"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(roomCallback)
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}