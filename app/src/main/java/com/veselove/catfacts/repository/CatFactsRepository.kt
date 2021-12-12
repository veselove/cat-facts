package com.veselove.catfacts.repository

import com.veselove.catfacts.api.CatFactsAPI
import com.veselove.catfacts.api.RetrofitInstance
import com.veselove.catfacts.db.CatFactsDao
import com.veselove.catfacts.models.CatFact
import javax.inject.Inject

class CatFactsRepository @Inject constructor(
    private val catFactsApi: CatFactsAPI, private val catFactsDAO: CatFactsDao
): MainRepository {
    override suspend fun getCatFact() = catFactsApi.getCatFact()

    override suspend fun upsert(fact: CatFact) = catFactsDAO.upsert(fact)

    override fun getSavedCatFacts() = catFactsDAO.getAllCatFacts()

    override suspend fun deleteCatFact(fact: CatFact) = catFactsDAO.deleteCatFact(fact)


//    suspend fun getCatFact() = RetrofitInstance.api.getCatFact()

//    suspend fun upsert(fact: CatFact) = catFactsDao.upsert(fact)
//
//    fun getSavedCatFacts() = catFactsDao.getAllCatFacts()
//
//    suspend fun deleteCatFact(fact: CatFact) = catFactsDao.deleteCatFact(fact)
}

/*
class CatFactsRepository(
    private val catFactsDao: CatFactsDao
)
 */