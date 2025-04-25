

package com.example.contactapp.data.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun upsertContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT * FROM contacts_table ORDER BY 'user name' ASC")
    fun getContactSortedByName(): Flow<List<Contact>>

    @Query("SELECT * FROM contacts_table ORDER BY dateOfCreation ASC")
    fun getContactSortedByDate(): Flow<List<Contact>>

    @Query("DELETE FROM contacts_table WHERE id = :contactId")
    suspend fun deleteContactById(contactId: Int)

    @Query("SELECT * FROM contacts_table")
    fun getAllContacts(): Flow<List<Contact>>


}
