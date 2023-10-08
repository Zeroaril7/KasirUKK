package com.example.kasirukk.data.menu

import com.ukk.data.GetFavorite
import com.ukk.data.Menu
import kotlinx.coroutines.flow.Flow

interface MenuDataSource {
    fun getMenuById(id: Long): Menu?

    fun getMenuByType(type: String): Flow<List<Menu>>

    fun getAllMenu(): Flow<List<Menu>>

    fun getFavorite() : List<GetFavorite?>
    suspend fun updateMenu(id: Long, name_menu: String, description: String, price: Long)

    suspend fun deleteMenuById(id: Long)

    suspend fun insertUser(name_menu: String, type: String, description: String, price: Long)
}