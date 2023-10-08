package com.example.kasirukk.data.detail_transaksi

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ukk.Database
import com.ukk.data.Detail_transaksi
import com.ukk.data.GetBill
import com.ukk.data.GetDetailTransaksiById
import com.ukk.data.GetOrder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class DetailTransactionDataSourceImpl(db: Database) : DetailTransactionDataSource {

    val queries = db.detailTransaksiQueries
    override fun getDetailTransaksiById(id_transaksi: Long): Flow<List<GetDetailTransaksiById>> {
        return queries.getDetailTransaksiById(id_transaksi).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getOrder(id_transaksi: Long): Flow<List<GetOrder>> {
        return  queries.getOrder(id_transaksi).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getBill(id_transaksi: Long): GetBill {
        return queries.getBill(id_transaksi).executeAsOne()
    }

    override fun getMenuData(id_transaksi: Long, id_menu: Long) : Detail_transaksi? {
        return queries.getMenuDetailTransaksi(id_transaksi, id_menu).executeAsOneOrNull()
    }

    override suspend fun deleteDetailTransaksi(id_menu: Long) {
        return withContext(Dispatchers.IO){
            queries.deleteDetailTransaksi(id_menu)
        }
    }

    override suspend fun insertDetailTransaksi(id_transaksi: Long, id_menu: Long, amount: Long, price: Long) {
        return withContext(Dispatchers.IO){
            queries.insertDetailTransaksi(id_transaksi, id_menu, amount, price)
        }
    }

    override suspend fun updateDetailTransaksi(id_menu: Long, amount: Long, price: Long) {
        return withContext(Dispatchers.IO){
            queries.updateDetailTransaksi(id_menu = id_menu, amount = amount, price = price)
        }
    }
}