package com.veselove.catfacts.di

import android.content.Context
import androidx.room.Room
import com.veselove.catfacts.api.CatFactsAPI
import com.veselove.catfacts.db.CatFactsDao
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.repository.CatFactsRepository
import com.veselove.catfacts.repository.MainRepository
import com.veselove.catfacts.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideCatFactsAPI(): CatFactsAPI = Retrofit.Builder()
        .baseUrl(Constants.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(CatFactsAPI::class.java)

    @Singleton
    @Provides
    fun provideCatFactsDatabase(
        @ApplicationContext app: Context
    ) = Room.databaseBuilder(
        app, CatFactsDatabase::class.java,"cat_facts_db.db"
    ).build()

    @Singleton
    @Provides
    fun provideCatFactsDao(db: CatFactsDatabase) = db.getCatFactsDao()

    @Singleton
    @Provides
    fun provideRepository(catFactsApi: CatFactsAPI, catFactsDao: CatFactsDao) : MainRepository = CatFactsRepository(catFactsApi, catFactsDao)

}