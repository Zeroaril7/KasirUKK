package com.example.kasirukk.data.report

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
import korlibs.time.DateTime
import kotlinx.coroutines.flow.Flow

interface ReportDataSource {

    fun getAllReturn() : GetAllReturn?

    fun getTrxByDate(date: DateTime): Flow<List<GetTrxByDate?>>

    fun getReturnByDate(date: DateTime) : GetReturnByDate?

    fun getTrxByUser(id_user: Long) : Flow<List<GetTrxByUser?>>

    fun getReturnByUser(id_user: Long) : GetReturnByUser?

    fun getTrxByMonthly(start_day: DateTime, end_day: DateTime) : Flow<List<GetTrxByMonthly?>>

    fun getReturnByMonthly(start_day: DateTime, end_day: DateTime) : GetReturnByMonthly?

    fun getTrxByUserAndDate(id_user: Long, date: DateTime) : Flow<List<GetTrxByUserAndDate?>>

    fun getReturnByUserAndDate(id_user: Long, date: DateTime) : GetReturnByUserAndDate?

    fun getTrxByUserAndMonthly(id_user: Long, start_day: DateTime, end_day: DateTime) : Flow<List<GetTrxByUserAndMonthly?>>

    fun getReturnByUserAndMonthly(id_user: Long, start_day: DateTime, end_day: DateTime) : GetReturnByUserAndMonthly?

}