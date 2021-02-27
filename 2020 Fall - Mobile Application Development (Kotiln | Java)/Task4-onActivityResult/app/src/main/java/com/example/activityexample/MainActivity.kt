package com.example.activityexample

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private val LAUNCH_SECOND_ACTIVITY = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun btnClick(View: View) {
        val intent = Intent(this, SecondActivity::class.java)
        val lessonName = findViewById<TextView>(R.id.textView).text.toString()
        intent.putExtra("lessonName", lessonName)
        startActivityForResult(intent, LAUNCH_SECOND_ACTIVITY);
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            val result = data?.getStringExtra("result")
            findViewById<TextView>(R.id.textView).text = result
        }
        if (resultCode == RESULT_CANCELED) {
            Toast.makeText(this, "You canceled this activity", Toast.LENGTH_SHORT).show()
        }
    }


}