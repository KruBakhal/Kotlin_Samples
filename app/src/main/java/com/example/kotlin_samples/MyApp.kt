package com.example.kotlin_samples

import android.app.Application
import android.content.Context

public class MyApp : Application() {
    lateinit var contextMyApp: Context
    override fun onCreate() {
        super.onCreate()
        contextMyApp=applicationContext
    }
}