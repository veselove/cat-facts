package com.veselove.catfacts.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.veselove.catfacts.models.CatFact

@Dao
interface CatFactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(fact: CatFact): Long

    @Query("SELECT * FROM facts")
    fun getAllCatFacts(): LiveData<List<CatFact>>

    @Delete
    suspend fun deleteCatFact(fact: CatFact)

}