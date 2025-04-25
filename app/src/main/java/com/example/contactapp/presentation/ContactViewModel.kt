package com.example.contactapp.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.contactapp.data.Database.Contact
import com.example.contactapp.data.Database.ContactDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.SharingStarted.Companion.WhileSubscribed
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(var dataBase: ContactDatabase) : ViewModel() {
    private var isSortedName = MutableStateFlow(true)
    private var contact = isSortedName.flatMapLatest {
        if
                (it) {
            dataBase.getDao().getContactSortedByName()
        } else {
            dataBase.getDao().getContactSortedByName()
        }

    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())


    val _state = MutableStateFlow(ContactState())
    val state = combine(_state, contact, isSortedName) { state, contacts, isSortedByName ->
        state.copy(contacts = contacts)
    }.stateIn(viewModelScope, WhileSubscribed(5000), ContactState())

    fun changeisSorting() {
        isSortedName.value = !isSortedName.value
    }

    fun saveContact() {
        val contact = Contact(
            id = _state.value.id.value,
            name = _state.value.name.value,
            phone = _state.value.phone.value,
            email = _state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = _state.value.image.value
        )

        viewModelScope.launch {
            dataBase.getDao().upsertContact(contact)
        }
        _state.value.id.value = 0
        _state.value.name.value = ""
        _state.value.phone.value = ""
        _state.value.email.value = ""
        _state.value.dateOfCreation.value = 0
        _state.value.image.value = null
    }

    fun clearForm() {
        val contact = Contact(
            id = _state.value.id.value,
            name = _state.value.name.value,
            phone = _state.value.phone.value,
            email = _state.value.email.value,
            isActive = true,
            dateOfCreation = System.currentTimeMillis().toLong(),
            image = _state.value.image.value
        )
        viewModelScope.launch {
            dataBase.getDao().upsertContact(contact)
        }
        _state.value.id.value = 0
        _state.value.name.value = ""
        _state.value.phone.value = ""
        _state.value.email.value = ""
        _state.value.dateOfCreation.value = 0
        _state.value.image.value = null
    }

    fun deleteContact(id: Int) {
        viewModelScope.launch {
            dataBase.getDao().deleteContactById(id)

        }
    }

}