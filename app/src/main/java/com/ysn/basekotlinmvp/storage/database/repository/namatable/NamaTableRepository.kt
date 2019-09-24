package com.ysn.basekotlinmvp.storage.database.repository.namatable

import com.ysn.basekotlinmvp.storage.database.dao.namatable.NamaTableDao
import com.ysn.basekotlinmvp.storage.database.entity.namatable.NamaTableEntity
import io.reactivex.Single
import javax.inject.Inject

class NamaTableRepository @Inject constructor(private val namaTableDao: NamaTableDao) {

    fun getAllNamaTableData(): Single<List<NamaTableEntity>> = namaTableDao.getAllNamaTable()

}