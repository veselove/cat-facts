package com.veselove.catfacts.repository

import androidx.lifecycle.LiveData
import com.veselove.catfacts.models.CatFact

interface MainRepository {

    suspend fun getCatFact(): CatFact

    suspend fun upsert(fact: CatFact): Long

    fun getSavedCatFacts(): LiveData<List<CatFact>>

    suspend fun deleteCatFact(fact: CatFact)

}