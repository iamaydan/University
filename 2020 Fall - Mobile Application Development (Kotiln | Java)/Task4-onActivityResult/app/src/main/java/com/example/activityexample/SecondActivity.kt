package com.example.activityexample

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val lessonName = intent.getStringExtra("lessonName")
        findViewById<EditText>(R.id.editTextTextPersonName).setText(lessonName)
    }

    fun onSubmit(view: View) {
        val returnIntent = Intent();
        val text = findViewById<EditText>(R.id.editTextTextPersonName).text.toString()
        returnIntent.putExtra("result", text);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}