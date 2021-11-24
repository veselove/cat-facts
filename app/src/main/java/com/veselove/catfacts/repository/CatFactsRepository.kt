package com.veselove.catfacts.repository

import android.util.Log
import com.veselove.catfacts.api.RetrofitInstance
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact

class CatFactsRepository(
    val db: CatFactsDatabase
) {
    suspend fun getCatFact() = RetrofitInstance.api.getCatFact()

    fun upsert(fact: CatFact) = db.getCatFactsDao().upsert(fact)

    fun getSavedCatFacts() = db.getCatFactsDao().getAllCatFacts()

    fun deleteCatFact(fact: CatFact) = db.getCatFactsDao().deleteCatFact(fact)
}