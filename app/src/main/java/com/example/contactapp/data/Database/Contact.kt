package com.example.contactapp.data.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts_table")
data class Contact (
@PrimaryKey(autoGenerate = true)
val id:Int=0,
    val name:String,
    val phone: String,
    val email:String,
    val isActive: Boolean,
    val dateOfCreation:Long,
    val image: ByteArray?=null,

)