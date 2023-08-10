package com.example.kasirukk.feature.admin.ui.user.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ukkkasir.data.user.UserDataSource
import com.ukk.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminUserViewModel @Inject constructor
    (private val userDataSource: UserDataSource): ViewModel() {

    var users = userDataSource.getAllUsers()
    var userDetails = MutableLiveData<User>()

    init {
        users = userDataSource.getAllUsers()
    }

    fun insertUser(name_user: String, username: String, password: String, role: String) {

        if (name_user.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            return
        }

        viewModelScope.launch {
            userDataSource.insertUser(name_user, username, password, role)
        }
    }

    fun updateUser(id: Long, name_user: String, username: String, password: String, role: String) {
        if (name_user.isEmpty() || username.isEmpty() || password.isEmpty() || role.isEmpty()) {
            return
        }

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
}