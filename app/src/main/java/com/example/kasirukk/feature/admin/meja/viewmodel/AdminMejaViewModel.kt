package com.example.kasirukk.feature.admin.meja.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasirukk.data.meja.MejaDataSource
import com.ukk.data.Meja
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AdminMejaViewModel @Inject constructor
    (private val mejaDataSource: MejaDataSource) : ViewModel() {

    var mejas = mejaDataSource.getAllMeja()
    var freeMejas = mejaDataSource.getFreeMeja()

    init {
        mejas = mejaDataSource.getAllMeja()
        freeMejas = mejaDataSource.getFreeMeja()
    }

    fun insertMeja(number: Long){
        viewModelScope.launch {
            mejaDataSource.insertMeja(number)
        }
    }

    fun updateMeja(id: Long, number: Long, isUsed: Boolean){
        viewModelScope.launch {
            mejaDataSource.updateMeja(id, number, isUsed)
        }
    }

    fun deleteMeja(id: Long){
        viewModelScope.launch {
            mejaDataSource.deleteMejaById(id)
        }
    }

    fun getMejaById(id: Long) : Meja? {
        return mejaDataSource.getMejaById(id)
    }

    fun getMejaByNumber(number: Long) : Meja {
        return mejaDataSource.getMejaByNumber(number)
    }
}