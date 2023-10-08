package com.example.kasirukk.data.transaksi

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ukk.Database
import com.ukk.data.Transaksi
import korlibs.time.DateTime
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TransactionDataSourceImpl(db: Database) : TransactionDataSource {

    private val queries = db.transaksiQueries

    override fun getTransactionById(id: Long): Transaksi? {
        return queries.getTransactionById(id).executeAsOneOrNull()

    }

    override fun getLastIdTransaction(): Long? {
        return queries.getLastIdTransaction().executeAsOneOrNull()
    }

    override fun getOnGoingTransaction(): Flow<List<Transaksi?>> {
        return queries.getOnGoingTransaction().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getCompleteTransaction(): Flow<List<Transaksi?>> {
        return queries.getCompleteTransaction().asFlow().mapToList(Dispatchers.IO)
    }

    override fun getAllTransaction(): Flow<List<Transaksi>> {
        return queries.getAllTransaction().asFlow().mapToList(Dispatchers.IO)
    }

    override suspend fun updateTransaction(id: Long, isPaid: Boolean) {
        return withContext(Dispatchers.IO){
            queries.updatePaidTransaction(
                is_paid = isPaid,
                id_transaksi = id
            )
        }
    }

    override suspend fun updateNote(id: Long, note: String) {
        return withContext(Dispatchers.IO){
            queries.updateNoteTransaction(id_transaksi = id, note = note)
        }
    }

    override suspend fun insertTransaction(
        id_transaksi : Long,
        created_at : DateTime,
        id_user: Long,
        id_meja: Long,
        buyer_name: String,
        note: String,
        is_paid: Boolean
    ) {
        return withContext(Dispatchers.IO){
            queries.insertTransaction(
                id_transaksi = id_transaksi,
                created_at = created_at,
                id_user = id_user,
                id_meja = id_meja,
                buyer_name = buyer_name,
                note = note,
                is_paid = is_paid
            )
        }
    }
}