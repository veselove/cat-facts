package com.veselove.catfacts.api

import com.veselove.catfacts.models.CatFact
import retrofit2.http.GET

interface CatFactsAPI {

    @GET("fact/")
    suspend fun getCatFact() : CatFact
}