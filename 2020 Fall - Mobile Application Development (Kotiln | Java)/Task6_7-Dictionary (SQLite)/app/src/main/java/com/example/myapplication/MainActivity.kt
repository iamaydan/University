package com.example.myapplication

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {
    var myDB: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val editText = findViewById<EditText>(R.id.editText)
        val editText2 = findViewById<EditText>(R.id.editText2)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        val btnView = findViewById<Button>(R.id.btnView)
        myDB = DatabaseHelper(this)
        btnAdd!!.setOnClickListener {
            val newEntry = editText!!.text.toString()
            val newEntry2 = editText2!!.text.toString()
            if (editText.length() != 0 && editText2.length() != 0) {
                addData(newEntry, newEntry2)
                editText.setText("")
                editText2.setText("")
            } else {
                Toast.makeText(this, "You must put something in the text field!", Toast.LENGTH_LONG)
                    .show()
            }
        }
        btnView!!.setOnClickListener {
            val intent = Intent(this, ListActivity::class.java)
            startActivity(intent)
        }

    }

    private fun addData(newEntry: String?, newEntry2: String?) {
        val insertData = myDB!!.addData(newEntry, newEntry2)
        if (insertData) {
            Toast.makeText(
                this,
                "Word ~${newEntry?.toUpperCase(Locale.ROOT)}~ added to dictionary",
                Toast.LENGTH_SHORT
            ).show()
        } else {
            Toast.makeText(this, "Something went wrong :(.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}