package fr.f5rpn.dbaccessv2.ui.add

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AddViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the Add Fragment"
    }
    val text: LiveData<String> = _text


}