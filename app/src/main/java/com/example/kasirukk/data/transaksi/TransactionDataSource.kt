package com.example.kasirukk.data.transaksi

import com.ukk.data.Transaksi
import korlibs.time.DateTime
import kotlinx.coroutines.flow.Flow

interface TransactionDataSource {
    fun getTransactionById(id: Long): Transaksi?

    fun getLastIdTransaction(): Long?

    fun getOnGoingTransaction() : Flow<List<Transaksi?>>

    fun getCompleteTransaction() : Flow<List<Transaksi?>>

    fun getAllTransaction(): Flow<List<Transaksi>>

    suspend fun updateTransaction(id: Long, isPaid: Boolean)

    suspend fun updateNote(id: Long, note: String)

    suspend fun insertTransaction(
        id_transaksi: Long,
        created_at : DateTime,
        id_user: Long,
        id_meja: Long,
        buyer_name: String,
        note: String,
        is_paid: Boolean
    )
}