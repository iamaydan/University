package com.example.lifecycleeventsexample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()
        Log.v("Lifecycle event", "onStart() called")
    }

    override fun onResume() {
        super.onResume()
        Log.v("Lifecycle event", "onResume() called")

    }

    override fun onPause() {
        super.onPause()
        Log.v("Lifecycle event", "onPause() called")

    }

    override fun onStop() {
        super.onStop()
        Log.v("Lifecycle event", "onStop() called")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.v("Lifecycle event", "onDestroy() called")

    }

    //additional
    override fun onRestart() {
        super.onRestart()
        Log.v("Lifecycle event", "onRestart() called")

    }


}