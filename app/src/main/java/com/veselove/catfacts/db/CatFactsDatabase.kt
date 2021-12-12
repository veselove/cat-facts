package com.veselove.catfacts.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.veselove.catfacts.models.CatFact

@Database(
    entities = [CatFact::class],
    version = 1
)

abstract class CatFactsDatabase : RoomDatabase() {

    abstract fun getCatFactsDao(): CatFactsDao

/*                                                  before Dagger Hilt implementation

    companion object {
        @Volatile
        private var INSTANCE: CatFactsDatabase? = null

        fun getDatabase(context: Context): CatFactsDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CatFactsDatabase::class.java,
                    "cat_facts_db.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
*/
}