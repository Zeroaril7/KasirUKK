package com.example.kasirukk.feature.admin.menu.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasirukk.data.menu.MenuDataSource
import com.ukk.Menu
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminMenuViewModel @Inject constructor
    (private val menuDataSource: MenuDataSource) : ViewModel() {

    var menus = menuDataSource.getAllMenu()
    var menuMakanan = menuDataSource.getMenuByType("Makanan")
    var menuMinuman = menuDataSource.getMenuByType("Minuman")
    private var menuDetails = MutableLiveData<Menu>()

        init {
            menus = menuDataSource.getAllMenu()
        }

    fun insertMenu(name_menu: String, type: String, description: String, price: Long){

        if (name_menu.isEmpty() || type.isEmpty() || description.isEmpty() || price.equals(0)){
            return
        }

        viewModelScope.launch {
            menuDataSource.insertUser(name_menu = name_menu, type = type, description = description, price = price)
        }
    }

    fun updateMenu(id: Long, name_menu: String, type: String, description: String, price: Long){
        if (name_menu.isEmpty() || type.isEmpty() || description.isEmpty() || price.equals(0)){
            return
        }

        viewModelScope.launch{
            menuDataSource.updateMenu(id, name_menu, type, description, price)
        }
    }

    fun deleteMenu(id: Long){
        viewModelScope.launch {
            menuDataSource.deleteMenu(id)
        }
    }

    fun getMenuById(id: Long){
        viewModelScope.launch {
            menuDetails.value = menuDataSource.getMenuById(id)
        }
    }
}