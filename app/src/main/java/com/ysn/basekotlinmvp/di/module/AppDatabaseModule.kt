package com.ysn.basekotlinmvp.di.module

import android.app.Application
import androidx.room.Room
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ysn.basekotlinmvp.storage.database.AppDatabase
import com.ysn.basekotlinmvp.storage.database.dao.namatable.NamaTableDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppDatabaseModule {

    @Provides
    @Singleton
    fun providesAppDatabase(application: Application): AppDatabase =
        Room.databaseBuilder(application, AppDatabase::class.java, "app_name.db")
            /*.addMigration(migration2)*/
            .fallbackToDestructiveMigration()
            /*.allowMainThreadQueries()*/
            .build()

    private var migration2 = object : Migration(1, 2) {
        override fun migrate(database: SupportSQLiteDatabase) {
            val queryAlterTable = "ALTER TABLE nama_table ADD COLUMN nama_column TEXT NOT NULL DEFAULT '-'"
            database.execSQL(queryAlterTable)
        }
    }

    @Provides
    @Singleton
    fun providesNamaTableDao(appDatabase: AppDatabase): NamaTableDao = appDatabase.namaTableDao()
}
