package fr.f5rpn.dbaccessv2.ui.update

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UpdateViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Update Fragment"
    }
    val text: LiveData<String> = _text
}