package com.example.kasirukk.data.meja

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ukk.Database
import com.ukk.data.Meja
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MejaDataSourceImpl(db: Database): MejaDataSource {

    private val queries = db.mejaQueries

    override fun getMejaById(id: Long): Meja? {
        return queries.getMejaById(id).executeAsOneOrNull()

    }

    override fun getMejaByNumber(number: Long): Meja {
        return queries.getMejaByNumber(number).executeAsOne()
    }

    override fun getAllMeja(): Flow<List<Meja>> {
        return queries.getAllMeja().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getFreeMeja(): Flow<List<Meja?>> {
        return queries.getFreeMeja().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun updateMeja(id: Long, number: Long, isUsed: Boolean) {
        return withContext(Dispatchers.IO){
            queries.updateMeja(
                number = number,
                is_used = isUsed,
                id_meja = id
            )
        }
    }

    override suspend fun deleteMejaById(id: Long) {
        return withContext(Dispatchers.IO){
            queries.deleteMeja(id)
        }
    }

    override suspend fun insertMeja(number: Long) {
        return withContext(Dispatchers.IO){
            queries.insertMeja(
                number = number
            )
        }
    }
}