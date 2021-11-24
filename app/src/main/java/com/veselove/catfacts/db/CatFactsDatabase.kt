package com.veselove.catfacts.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.MainActivity

@Database(
    entities = [CatFact::class],
    version = 1
)

abstract class CatFactsDatabase : RoomDatabase() {

    abstract fun getCatFactsDao(): CatsFactsDao

    companion object {
        private var instance: CatFactsDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: MainActivity) = instance ?: synchronized(LOCK) {
            instance ?: createDataBase(context).also { instance = it}
        }

        private fun createDataBase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                CatFactsDatabase::class.java,
                "cat_facts_db.db"
            ).build()
    }
}