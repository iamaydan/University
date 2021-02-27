package com.example.task1

import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private var word = ""
    private lateinit var wordList: MutableList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        resetList()
    }

    private fun resetList() {
        wordList = mutableListOf(
            "queen",
            "hospital",
            "basketball",
            "cat",
            "change",
            "snail",
            "soup",
            "calendar",
            "sad",
            "desk",
            "guitar",
            "home",
            "railway",
            "zebra",
            "jelly",
            "car",
            "crow",
            "trade",
            "bag",
            "roll",
            "bubble"
        )
        wordList.shuffle()
    }


    private fun nextWord() {
        if (wordList.isEmpty()) {
            resetList()
        } else {
            word = wordList.removeAt(0)
        }
        updateWordText()
    }

    private fun updateWordText() {
        findViewById<TextView>(R.id.textView).text = word.capitalize(Locale.ROOT)
    }

    private fun showToast() {
        Toast.makeText(
            this,
            "text changed to '${word.capitalize(Locale.ROOT)}'",
            Toast.LENGTH_SHORT
        )
            .show()
    }

    fun onClick(view: View) {
        nextWord()
        showToast()
    }

}
