package com.ysn.basekotlinmvp.storage.database.dao.namatable

import androidx.room.Dao
import androidx.room.Query
import com.ysn.basekotlinmvp.storage.database.entity.namatable.NamaTableEntity
import io.reactivex.Single

@Dao
interface NamaTableDao {

    @Query("SELECT * FROM nama_table")
    fun getAllNamaTable(): Single<List<NamaTableEntity>>

}