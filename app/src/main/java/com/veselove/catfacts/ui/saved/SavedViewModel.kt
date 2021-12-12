package com.veselove.catfacts.ui.saved

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.veselove.catfacts.db.CatFactsDatabase
import com.veselove.catfacts.models.CatFact
import com.veselove.catfacts.repository.CatFactsRepository
import com.veselove.catfacts.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers

import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import javax.inject.Inject

@HiltViewModel
class SavedViewModel @Inject constructor (val repository: MainRepository): ViewModel() {

    //fun getSavedCatFacts() = repository.getSavedCatFacts() <<<before Kotlin's Flow implementation

    val getSavedCatFacts = repository.getSavedCatFacts().asFlow()

    fun deleteCatFact(fact: CatFact) = viewModelScope.launch {
        repository.deleteCatFact(fact)
    }

    fun saveCatFact(fact: CatFact) = viewModelScope.launch(Dispatchers.IO) {
        repository.upsert(fact)
    }
}




/*                                                  before Dagger Hilt implementation

class SavedViewModel(application: Application) : AndroidViewModel(application) {

    val catFactsDB = CatFactsDatabase.getDatabase(application).getCatFactsDao()
    val repository = CatFactsRepository(catFactsDB)

    val getSavedCatFacts = repository.getSavedCatFacts().asFlow()

    fun deleteCatFact(fact: CatFact) = viewModelScope.launch {
        repository.deleteCatFact(fact)
    }

    fun saveCatFact(fact: CatFact) = viewModelScope.launch(Dispatchers.IO) {
        repository.upsert(fact)
    }
}
 */


