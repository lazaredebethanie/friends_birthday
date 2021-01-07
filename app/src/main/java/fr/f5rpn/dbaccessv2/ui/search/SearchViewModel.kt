package fr.f5rpn.dbaccessv2.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SearchViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Search Fragment"
    }
    val text: LiveData<String> = _text
}