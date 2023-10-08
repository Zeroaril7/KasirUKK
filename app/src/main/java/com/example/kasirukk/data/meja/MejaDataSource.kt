package com.example.kasirukk.data.meja

import com.ukk.data.Meja
import kotlinx.coroutines.flow.Flow

interface MejaDataSource {
    fun getMejaById(id: Long): Meja?

    fun getMejaByNumber(number: Long): Meja

    fun getAllMeja(): Flow<List<Meja>>

    fun getFreeMeja(): Flow<List<Meja?>>

    suspend fun updateMeja(id: Long, number: Long, isUsed: Boolean)

    suspend fun deleteMejaById(id: Long)

    suspend fun insertMeja(number: Long)
}