package com.veselove.catfacts.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.repository.CatFactsRepository
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import okhttp3.Dispatcher

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    val catFactsDB = CatFactsDatabase.getDatabase(application).getCatFactsDao()
    val repository = CatFactsRepository(catFactsDB)

    fun getSavedCatFacts() = repository.getSavedCatFacts()

    fun deleteCatFact(fact: CatFact) = viewModelScope.launch {
        repository.deleteCatFact(fact)
    }

}