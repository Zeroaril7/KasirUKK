package com.example.kasirukk.data.menu

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ukk.Database
import com.ukk.Menu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MenuDataSourceImpl(db: Database) : MenuDataSource {

    private val queries = db.menuQueries

    override suspend fun getMenuById(id: Long): Menu? {
        return withContext(Dispatchers.IO){
            queries.getMenuById(id).executeAsOneOrNull()
        }
    }

    override fun getMenuByType(type: String): Flow<List<Menu>> {
        return queries.getMenuByType(type).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getAllMenu(): Flow<List<Menu>> {
        return queries.getAllMenu().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun updateMenu(
        id: Long,
        name_menu: String,
        type: String,
        description: String,
        price: Long
    ) {
        return withContext(Dispatchers.IO){
            queries.updateMenu(
                id_menu = id,
                name_menu = name_menu,
                type = type,
                description = description,
                price = price
            )
        }
    }

    override suspend fun deleteMenu(id: Long) {
        return withContext(Dispatchers.IO){
            queries.deleteMenu(id)
        }
    }

    override suspend fun insertUser(
        name_menu: String,
        type: String,
        description: String,
        price: Long
    ) {
        return withContext(Dispatchers.IO){
            queries.insertMenu(
                name_menu = name_menu,
                type = type,
                description = description,
                price = price
            )
        }
    }
}