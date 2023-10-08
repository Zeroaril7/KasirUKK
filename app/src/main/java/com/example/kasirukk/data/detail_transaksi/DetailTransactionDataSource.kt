package com.example.kasirukk.data.detail_transaksi

import com.ukk.data.Detail_transaksi
import com.ukk.data.GetBill
import com.ukk.data.GetDetailTransaksiById
import com.ukk.data.GetOrder
import kotlinx.coroutines.flow.Flow

interface DetailTransactionDataSource {
    fun getDetailTransaksiById(id_transaksi: Long) : Flow<List<GetDetailTransaksiById>>

    fun getOrder(id_transaksi: Long) : Flow<List<GetOrder>>

    fun getBill(id_transaksi: Long) : GetBill

    fun getMenuData(id_transaksi: Long, id_menu: Long) : Detail_transaksi?

    suspend fun deleteDetailTransaksi(id_menu: Long)

    suspend fun insertDetailTransaksi(id_transaksi: Long, id_menu: Long, amount: Long, price: Long)

    suspend fun updateDetailTransaksi(id_menu: Long, amount: Long, price: Long)


}