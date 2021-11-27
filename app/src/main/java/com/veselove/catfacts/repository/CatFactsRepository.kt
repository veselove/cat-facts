package com.veselove.catfacts.repository

import com.veselove.catfacts.api.RetrofitInstance
import com.veselove.catfacts.db.CatFactsDao
import com.veselove.catfacts.models.CatFact

class CatFactsRepository(
    private val catFactsDao: CatFactsDao
) {
    suspend fun getCatFact() = RetrofitInstance.api.getCatFact()

    suspend fun upsert(fact: CatFact) = catFactsDao.upsert(fact)

    fun getSavedCatFacts() = catFactsDao.getAllCatFacts()

    suspend fun deleteCatFact(fact: CatFact) = catFactsDao.deleteCatFact(fact)
}