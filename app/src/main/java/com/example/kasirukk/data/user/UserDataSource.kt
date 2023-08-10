package com.example.ukkkasir.data.user

import com.ukk.User
import kotlinx.coroutines.flow.Flow


interface UserDataSource {
    suspend fun getUserById(id: Long): User?

    fun getAllUsers(): Flow<List<User>>

    suspend fun updateUser(id: Long, name_user: String, username: String, password: String, role: String)

    suspend fun deleteUserById(id: Long)

    suspend fun insertUser(name_user: String, username: String, password: String, role: String)
}