package com.example.kasirukk.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.kasirukk.helper.dateAdapter
import com.example.kasirukk.data.detail_transaksi.DetailTransactionDataSource
import com.example.kasirukk.data.detail_transaksi.DetailTransactionDataSourceImpl
import com.example.kasirukk.data.meja.MejaDataSource
import com.example.kasirukk.data.meja.MejaDataSourceImpl
import com.example.kasirukk.data.menu.MenuDataSource
import com.example.kasirukk.data.menu.MenuDataSourceImpl
import com.example.kasirukk.data.report.ReportDataSource
import com.example.kasirukk.data.report.ReportDataSourceImpl
import com.example.kasirukk.data.transaksi.TransactionDataSource
import com.example.kasirukk.data.transaksi.TransactionDataSourceImpl
import com.example.ukkkasir.data.user.UserDataSource
import com.example.kasirukk.data.user.UserDataSourceImpl
import com.ukk.Database
import com.ukk.data.Transaksi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideSqlDriver(app: Application): SqlDriver{
        return AndroidSqliteDriver(
            schema = Database.Schema,
            context = app,
            name ="kasir.db",
        )
    }

    @Provides
    @Singleton
    fun providesUserDataSource(driver: SqlDriver): UserDataSource {
        return UserDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }

    @Provides
    @Singleton
    fun providesMenuDataSource(driver: SqlDriver): MenuDataSource {
        return MenuDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }

    @Provides
    @Singleton
    fun providesMejaDataSource(driver: SqlDriver): MejaDataSource {
        return MejaDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }

    @Provides
    @Singleton
    fun providesTransactionDataSource(driver: SqlDriver): TransactionDataSource {
        return TransactionDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }

    @Provides
    @Singleton
    fun providesDetailTransactionDataSource(driver: SqlDriver): DetailTransactionDataSource {
        return DetailTransactionDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }

    @Provides
    @Singleton
    fun providesReportDataSource(driver: SqlDriver): ReportDataSource {
        return ReportDataSourceImpl(
            Database(
                driver,
                transaksiAdapter = Transaksi.Adapter(
                    created_atAdapter = dateAdapter
                )
            )
        )
    }
}