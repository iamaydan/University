package com.example.layoutsexample.ui.relative

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RelativeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is relative layout example"
    }
    val text: LiveData<String> = _text
}