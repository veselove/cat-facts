package com.veselove.catfacts.ui.facts

import android.app.Application
import androidx.lifecycle.*
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.repository.CatFactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CatFactsRepository
    val catFact: MutableLiveData<CatFact> = MutableLiveData()
    private var response: CatFact = CatFact("none", 0)

    init {
        val catFactsDB = CatFactsDatabase.getDatabase(application).getCatFactsDao()
        repository = CatFactsRepository(catFactsDB)
        getCatFact()
    }

    fun getCatFact() = viewModelScope.launch {
        response = repository.getCatFact()
        catFact.postValue(response)
    }

    fun saveCatFact() = viewModelScope.launch(Dispatchers.IO) {
            repository.upsert(response)
    }

}