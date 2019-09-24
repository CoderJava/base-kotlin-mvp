package com.ysn.basekotlinmvp.storage.database.entity.namatable

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nama_table")
data class NamaTableEntity (
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_kolom")
    var idKolom: Long = 0,
    @ColumnInfo(name = "kolom1")
    var kolom1: String = ""
)