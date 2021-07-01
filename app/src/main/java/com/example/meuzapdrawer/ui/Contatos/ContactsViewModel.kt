package com.example.meuzapdrawer.ui.Contatos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.meuzapdrawer.model.Contact
import com.example.meuzapdrawer.repository.UserRepository
import com.firebase.ui.auth.data.model.User

class ContactsViewModel : ViewModel() {

    private val _ContactsList = MutableLiveData<ArrayList<Contact>>().apply {
        UserRepository.getMyContacts{
            value = it
        }
    }
    val contactList: MutableLiveData<ArrayList<Contact>> = _ContactsList
}