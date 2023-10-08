package com.example.kasirukk.data.report

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import com.ukk.Database
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow

class ReportDataSourceImpl(db: Database) : ReportDataSource {

    val queries = db.transaksiQueries
    override fun getAllReturn(): GetAllReturn? {
        return queries.getAllReturn().executeAsOneOrNull()
    }

    override fun getTrxByDate(date: DateTime): Flow<List<GetTrxByDate?>> {
        return queries.getTrxByDate(date).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getReturnByDate(date: DateTime): GetReturnByDate? {
        return queries.getReturnByDate(date).executeAsOneOrNull()
    }

    override fun getTrxByUser(id_user: Long): Flow<List<GetTrxByUser?>> {
        return queries.getTrxByUser(id_user).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getReturnByUser(id_user: Long): GetReturnByUser? {
        return queries.getReturnByUser(id_user).executeAsOneOrNull()
    }

    override fun getTrxByMonthly(
        start_day: DateTime,
        end_day: DateTime
    ): Flow<List<GetTrxByMonthly?>> {
        return queries.getTrxByMonthly(start_day, end_day).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getReturnByMonthly(start_day: DateTime, end_day: DateTime): GetReturnByMonthly? {
        return queries.getReturnByMonthly(start_day, end_day).executeAsOneOrNull()
    }

    override fun getTrxByUserAndDate(
        id_user: Long,
        date: DateTime
    ): Flow<List<GetTrxByUserAndDate?>> {
        return queries.getTrxByUserAndDate(id_user, date).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getReturnByUserAndDate(id_user: Long, date: DateTime): GetReturnByUserAndDate? {
        return queries.getReturnByUserAndDate(date, id_user).executeAsOneOrNull()
    }

    override fun getTrxByUserAndMonthly(
        id_user: Long,
        start_day: DateTime,
        end_day: DateTime
    ): Flow<List<GetTrxByUserAndMonthly?>> {
        return queries.getTrxByUserAndMonthly(id_user, start_day, end_day).asFlow().mapToList(Dispatchers.IO)
    }

    override fun getReturnByUserAndMonthly(
        id_user: Long,
        start_day: DateTime,
        end_day: DateTime
    ): GetReturnByUserAndMonthly? {
        return queries.getReturnByUserAndMonthly(id_user, start_day, end_day).executeAsOneOrNull()
    }
}