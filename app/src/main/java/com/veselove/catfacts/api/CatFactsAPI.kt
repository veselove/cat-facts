package com.veselove.catfacts.api

import com.veselove.catfacts.models.CatFact
import retrofit2.http.GET

interface CatFactsAPI {

    @GET("fact/")
    suspend fun getCatFact() : CatFact
}




/*                       before Dagger Hilt implementation there was this class in the api package

class RetrofitInstance {

    companion object {

        private val retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        val api by lazy {
            retrofit.create(CatFactsAPI::class.java)
        }
    }

}
 */