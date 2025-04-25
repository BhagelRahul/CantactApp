package com.example.contactapp.data.Database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Contact::class], version = 2, exportSchema = false)
 abstract class ContactDatabase: RoomDatabase() {
     abstract fun getDao():Dao

}