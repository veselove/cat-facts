package com.veselove.catfacts.repository

import android.util.Log
import com.veselove.catfacts.api.RetrofitInstance
import com.veselove.catfacts.db.CatFactsDao
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact

class CatFactsRepository(
    private val catFactsDao: CatFactsDao
) {
    suspend fun getCatFact() = RetrofitInstance.api.getCatFact()

    fun upsert(fact: CatFact) = catFactsDao.upsert(fact)

    fun getSavedCatFacts() = catFactsDao.getAllCatFacts()

    fun deleteCatFact(fact: CatFact) = catFactsDao.deleteCatFact(fact)
}