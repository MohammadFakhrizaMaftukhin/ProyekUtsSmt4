package com.example.proyekutssmt4

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application) {
    private val dao = AppDatabase.getInstance(application).userDao()
    val currentUser = MutableLiveData<User?>()

    fun register(user: User) = viewModelScope.launch {
        dao.insert(user)
    }

    fun login(email: String, password: String) = viewModelScope.launch {
        currentUser.postValue(dao.login(email, password))
    }

    fun update(user: User) = viewModelScope.launch {
        dao.update(user)
        currentUser.postValue(user)
    }

    fun observeUser(email: String): LiveData<User> {
        return dao.observeUserByEmail(email)
    }

}