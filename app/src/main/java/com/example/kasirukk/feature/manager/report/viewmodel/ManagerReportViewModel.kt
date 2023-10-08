package com.example.kasirukk.feature.manager.report.viewmodel

import androidx.lifecycle.ViewModel
import com.example.kasirukk.data.report.ReportDataSource
import com.ukk.data.GetAllReturn
import com.ukk.data.GetReturnByDate
import com.ukk.data.GetReturnByMonthly
import com.ukk.data.GetReturnByUser
import com.ukk.data.GetReturnByUserAndDate
import com.ukk.data.GetReturnByUserAndMonthly
import com.ukk.data.GetTrxByDate
import com.ukk.data.GetTrxByMonthly
import com.ukk.data.GetTrxByUser
import com.ukk.data.GetTrxByUserAndDate
import com.ukk.data.GetTrxByUserAndMonthly
import dagger.hilt.android.lifecycle.HiltViewModel
import korlibs.time.DateTime
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class ManagerReportViewModel @Inject constructor(
    private val reportDataSource: ReportDataSource
) : ViewModel() {

    fun getAllReturn() : GetAllReturn? {
        return reportDataSource.getAllReturn()
    }

    fun getTrxByDate(date: DateTime): Flow<List<GetTrxByDate?>>{
        return reportDataSource.getTrxByDate(date)
    }

    fun getReturnByDate(date: DateTime) : GetReturnByDate? {
        return reportDataSource.getReturnByDate(date)
    }

    fun getTrxByUser(id_user: Long): Flow<List<GetTrxByUser?>>{
        return reportDataSource.getTrxByUser(id_user)
    }

    fun getReturnByUser(id_user: Long) : GetReturnByUser? {
        return reportDataSource.getReturnByUser(id_user)
    }

    fun getTrxByMonthly(
        start_day: DateTime,
        end_day: DateTime
    ): Flow<List<GetTrxByMonthly?>> {
        return reportDataSource.getTrxByMonthly(start_day, end_day)
    }

    fun getReturnByMonthly(start_day: DateTime, end_day: DateTime) : GetReturnByMonthly? {
        return reportDataSource.getReturnByMonthly(start_day, end_day)
    }

    fun getTrxByUserAndDate(
        id_user: Long,
        date: DateTime
    ): Flow<List<GetTrxByUserAndDate?>> {
        return reportDataSource.getTrxByUserAndDate(id_user, date)
    }

    fun getReturnByUserAndDate(id_user: Long, date: DateTime) : GetReturnByUserAndDate?{
        return reportDataSource.getReturnByUserAndDate(id_user, date)
    }


    fun getTrxByUserAndMonthly(
        id_user: Long,
        start_day: DateTime,
        end_day: DateTime
    ): Flow<List<GetTrxByUserAndMonthly?>> {
        return reportDataSource.getTrxByUserAndMonthly(id_user, start_day, end_day)
    }

    fun getReturnByUserAndMonthly(id_user: Long, start_day: DateTime, end_day: DateTime) : GetReturnByUserAndMonthly? {
        return reportDataSource.getReturnByUserAndMonthly(id_user, start_day, end_day)
    }
}