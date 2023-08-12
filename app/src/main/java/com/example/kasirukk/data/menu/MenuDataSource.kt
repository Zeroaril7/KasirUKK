package com.example.kasirukk.data.menu

import com.ukk.Menu
import kotlinx.coroutines.flow.Flow

interface MenuDataSource {
    suspend fun getMenuById(id: Long): Menu?

    fun getMenuByType(type: String): Flow<List<Menu>>

    fun getAllMenu(): Flow<List<Menu>>

    suspend fun updateMenu(id: Long, name_menu: String, type: String, description: String, price: Long)

    suspend fun deleteMenu(id: Long)

    suspend fun insertUser(name_menu: String, type: String, description: String, price: Long)
}