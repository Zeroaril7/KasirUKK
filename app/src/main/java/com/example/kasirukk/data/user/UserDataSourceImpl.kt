package com.example.kasirukk.data.user

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.example.ukkkasir.data.user.UserDataSource
import com.ukk.Database
import com.ukk.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class UserDataSourceImpl(db: Database): UserDataSource {

    private val queries = db.userQueries

    override suspend fun getUserById(id: Long): User? {
        return withContext(Dispatchers.IO){
            queries.getUserById(id).executeAsOneOrNull()
        }
    }

    override fun getUserByUsername(username: String): User? {
        return queries.getUserByUsername(username).executeAsOneOrNull()
    }

    override fun getUserByNameUser(name_user: String): User? {
        return queries.getUserByNameUser(name_user).executeAsOneOrNull()
    }

    override fun getAllUsers(): Flow<List<User>> {
        return queries.getAllUser().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getAllKasir(): Flow<List<User>> {
        return queries.getAllUserKasir().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun updateUser(
        id: Long,
        name_user: String,
        username: String,
        password: String,
        role: String
    ) {
        return withContext(Dispatchers.IO){
            queries.updateUser(
                id_user = id,
                name_user = name_user,
                username = username,
                password = password,
                role = role)
        }
    }

    override suspend fun deleteUserById(id: Long) {
        return withContext(Dispatchers.IO){
            queries.deleteUser(id)
        }
    }

    override suspend fun insertUser(
        name_user: String,
        username: String,
        password: String,
        role: String
    ) {
        return withContext(Dispatchers.IO){
            queries.insertUser(
                name_user = name_user,
                username = username,
                password = password,
                role = role
            )
        }
    }
}