package com.veselove.catfacts.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.repository.CatFactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CatFactsRepository
    private var readAll: LiveData<List<CatFact>>

    init {
        val catFactsDB = CatFactsDatabase.getDatabase(application).getCatFactsDao()
        repository = CatFactsRepository(catFactsDB)
        readAll = repository.getSavedCatFacts()
    }

}