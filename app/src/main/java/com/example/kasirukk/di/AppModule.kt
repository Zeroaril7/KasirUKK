package com.example.ukkkasir.di

import android.app.Application
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.example.ukkkasir.data.user.UserDataSource
import com.example.ukkkasir.data.user.UserDataSourceImpl
import com.ukk.Database
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
            name ="kasir.db"
        )
    }

    @Provides
    @Singleton
    fun providesUserDataSource(driver: SqlDriver): UserDataSource {
        return UserDataSourceImpl(Database(driver))
    }
}