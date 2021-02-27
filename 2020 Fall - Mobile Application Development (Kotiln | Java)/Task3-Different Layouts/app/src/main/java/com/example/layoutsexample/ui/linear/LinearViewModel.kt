package com.example.layoutsexample.ui.linear

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LinearViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is linear layout example. \nButtons 3, 4, and 5 are aligned horizontally while being inside the vertical orientation with buttons 1, 2, and 6."
    }
    val text: LiveData<String> = _text
}