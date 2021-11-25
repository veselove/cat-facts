package com.veselove.catfacts.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.veselove.catfacts.models.CatFact

@Dao
interface CatFactsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(fact: CatFact): Long

    @Query("SELECT * FROM facts")
    fun getAllCatFacts(): LiveData<List<CatFact>>

    @Delete
    fun deleteCatFact(fact: CatFact)

}