package com.example.kasirukk.feature.admin.menu.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasirukk.data.menu.MenuDataSource
import com.ukk.data.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminMenuViewModel @Inject constructor
    (private val menuDataSource: MenuDataSource) : ViewModel() {

    var menus = menuDataSource.getAllMenu()
    var menuMakanan = menuDataSource.getMenuByType("Makanan")
    var menuMinuman = menuDataSource.getMenuByType("Minuman")
    var favoriteMenu = menuDataSource.getFavorite()

    init {
        menus = menuDataSource.getAllMenu()
        favoriteMenu = menuDataSource.getFavorite()
    }

    fun insertMenu(name_menu: String, type: String, description: String, price: Long){
        viewModelScope.launch {
            menuDataSource.insertUser(name_menu = name_menu, type = type, description = description, price = price)
        }
    }

    fun updateMenu(id: Long, name_menu: String, description: String, price: Long){
        viewModelScope.launch{
            menuDataSource.updateMenu(id, name_menu, description, price)
        }
    }

    fun deleteMenu(id: Long){
        viewModelScope.launch {
            menuDataSource.deleteMenuById(id)
        }
    }

    fun getMenuById(id: Long): Menu? {
        return menuDataSource.getMenuById(id)
    }
}