package com.example.kotlin_samples

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MoreAppActivity : AppCompatActivity() {

    var context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_more_app)


    }
}