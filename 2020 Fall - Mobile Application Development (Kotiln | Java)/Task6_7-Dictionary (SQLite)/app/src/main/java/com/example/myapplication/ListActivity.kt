package com.example.myapplication

import android.database.Cursor
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ListActivity : AppCompatActivity() {
    var myDB: DatabaseHelper? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        val listView = findViewById<ListView>(R.id.listView)
        myDB = DatabaseHelper(this)

        val theList = ArrayList<String>()
        val data: Cursor = myDB!!.getListContents()!!
        if (data.count == 0) {
            Toast.makeText(this, "There are no contents in this list!", Toast.LENGTH_LONG).show()
        } else {
            while (data.moveToNext()) {
                theList.add(data.getString(1))
                val listAdapter: ListAdapter =
                    ArrayAdapter<Any?>(
                        this, android.R.layout.simple_list_item_1,
                        theList as List<Any?>
                    )
                listView.adapter = listAdapter
            }
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val data: Cursor = myDB!!.getListContents()!!
            if (data.moveToPosition(position)) {
                val engWord = data.getString(1)
                val azeWord = data.getString(2)
                showSimpleAlert("$engWord - $azeWord")
            }
        }
    }

    private fun showSimpleAlert(title: String) {
        AlertDialog.Builder(this)
            .setTitle(title)
            .setPositiveButton("OK") { _, _ -> }
            .show()
    }
}
