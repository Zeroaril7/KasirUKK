package com.example.kasirukk.feature.cashier.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kasirukk.data.detail_transaksi.DetailTransactionDataSource
import com.example.kasirukk.data.transaksi.TransactionDataSource
import com.ukk.data.GetBill
import com.ukk.data.GetDetailTransaksiById
import com.ukk.data.GetOrder
import com.ukk.data.Transaksi
import dagger.hilt.android.lifecycle.HiltViewModel
import korlibs.time.DateTime
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ukk.data.Detail_transaksi

@HiltViewModel
class CashierViewModel @Inject constructor(
    private val transactionDataSource: TransactionDataSource,
    private val detailTransactionDataSource: DetailTransactionDataSource
) : ViewModel() {

    var transactions = transactionDataSource.getAllTransaction()
    var onGoingTransactions = transactionDataSource.getOnGoingTransaction()
    var completeTransactions = transactionDataSource.getCompleteTransaction()

    init {
        transactions = transactionDataSource.getAllTransaction()
        onGoingTransactions = transactionDataSource.getOnGoingTransaction()
        completeTransactions = transactionDataSource.getCompleteTransaction()
    }

    fun insertTransaction(
        id_transaksi: Long,
        created_at: DateTime,
        id_user: Long,
        id_meja: Long,
        buyer_name: String,
        note: String,
        is_paid: Boolean
    ){
        viewModelScope.launch {
            transactionDataSource.insertTransaction(
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

    fun updateTransaction(id: Long, isPaid: Boolean){
        viewModelScope.launch {
            transactionDataSource.updateTransaction(id, isPaid)
        }
    }

    fun updateNoteTransaction(id: Long, note: String){
        viewModelScope.launch {
            transactionDataSource.updateNote(id, note)
        }
    }

    fun getTransactionById(id: Long) : Transaksi? = transactionDataSource.getTransactionById(id)

    fun getOrder(id_transaksi: Long) : Flow<List<GetOrder>> = detailTransactionDataSource.getOrder(id_transaksi)

    fun getLastIdTransaction(): Long? = transactionDataSource.getLastIdTransaction()

    fun getBill(id_transaksi: Long) : GetBill = detailTransactionDataSource.getBill(id_transaksi)

    fun getMenuData(id_transaksi: Long, id_menu: Long) : Detail_transaksi? = detailTransactionDataSource.getMenuData(id_transaksi, id_menu)

    fun insertDetailTransaction(id_transaksi: Long, id_menu: Long, amount: Long, price: Long){
        viewModelScope.launch {
            detailTransactionDataSource.insertDetailTransaksi(id_transaksi,id_menu, amount, price)
        }
    }

    fun deleteDetailTransaction(id_menu: Long){
        viewModelScope.launch {
            detailTransactionDataSource.deleteDetailTransaksi(id_menu)
        }
    }

    fun updateDetailTransaksi(id_menu: Long, amount: Long, price: Long){
        viewModelScope.launch {
            detailTransactionDataSource.updateDetailTransaksi(id_menu, amount, price)
        }
    }

    fun getDetailTransactionById(id_transaksi: Long) : Flow<List<GetDetailTransaksiById>> {
        return detailTransactionDataSource.getDetailTransaksiById(id_transaksi)
    }
}