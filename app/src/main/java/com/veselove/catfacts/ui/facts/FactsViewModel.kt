package com.veselove.catfacts.ui.facts

import android.app.Application
import androidx.lifecycle.*
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.repository.CatFactsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FactsViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: CatFactsRepository
//    val catFact: MutableLiveData<CatFact> = MutableLiveData()
    private var response: CatFact = CatFact("", 0)
    private val _catFact = MutableStateFlow(response)
    val catFact = _catFact.asStateFlow()

    init {
        val catFactsDB = CatFactsDatabase.getDatabase(application).getCatFactsDao()
        repository = CatFactsRepository(catFactsDB)
        getCatFact()
    }

    fun getCatFact() = viewModelScope.launch {
        response = repository.getCatFact()
//        catFact.postValue(response)
        _catFact.value = response
    }

    fun saveCatFact() = viewModelScope.launch(Dispatchers.IO) {
        repository.upsert(response)
    }

}