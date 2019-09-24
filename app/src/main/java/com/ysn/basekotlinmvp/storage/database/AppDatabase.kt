package com.ysn.basekotlinmvp.storage.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ysn.basekotlinmvp.storage.database.dao.namatable.NamaTableDao
import com.ysn.basekotlinmvp.storage.database.entity.namatable.NamaTableEntity

@Database(entities = [(NamaTableEntity::class)], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun namaTableDao(): NamaTableDao

}