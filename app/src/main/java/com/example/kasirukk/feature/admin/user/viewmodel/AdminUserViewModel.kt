package com.example.kasirukk.feature.admin.user.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ukkkasir.data.user.UserDataSource
import com.ukk.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AdminUserViewModel @Inject constructor
    (private val userDataSource: UserDataSource): ViewModel() {

    var users = userDataSource.getAllUsers()
    var kasirUsers = userDataSource.getAllKasir()
    private var userDetails = MutableLiveData<User>()

    init {
        users = userDataSource.getAllUsers()
        kasirUsers = userDataSource.getAllKasir()
    }

    fun insertUser(name_user: String, username: String, password: String, role: String) {
        viewModelScope.launch {
            userDataSource.insertUser(name_user, username, password, role)
        }
    }

    fun updateUser(id: Long, name_user: String, username: String, password: String, role: String) {
        viewModelScope.launch {
            userDataSource.updateUser(id, name_user, username, password, role)
        }
    }

    fun deleteUser(id: Long) {
        viewModelScope.launch {
            userDataSource.deleteUserById(id)
        }
    }

    fun getUserById(id: Long) {
        viewModelScope.launch {
            userDetails.value = userDataSource.getUserById(id)
        }
    }

    fun getUserByUsername(username: String) : User? {
        return userDataSource.getUserByUsername(username)
    }

    fun getUserByNameUser(name_user: String) : User? {
        return userDataSource.getUserByNameUser(name_user)
    }
}