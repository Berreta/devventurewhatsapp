package com.example.meuzapdrawer.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.meuzapdrawer.model.User
import com.example.meuzapdrawer.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LogInViewModel(private val repository: UserRepository): ViewModel() {


    fun addNewItem(user: User, onSuccess: () -> Unit, onFail: (error: String) -> Unit){
        viewModelScope.launch (Dispatchers.IO){
            repository.addUser(user, onSuccess, onFail)
        }
    }
}

class LoginViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogInViewModel(
                repository
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
