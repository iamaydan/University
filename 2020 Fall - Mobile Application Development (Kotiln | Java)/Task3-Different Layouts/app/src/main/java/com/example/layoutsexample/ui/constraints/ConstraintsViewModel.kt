package com.example.layoutsexample.ui.constraints

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ConstraintsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is constraints layout example"
    }
    val text: LiveData<String> = _text
}