package com.veselove.catfacts.ui.facts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FactsViewModel : ViewModel() {
    
    private val _text = MutableLiveData<String>().apply {
        value = "This is fact Fragment"
    }
    val text: LiveData<String> = _text
}