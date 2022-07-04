package com.example.cs496_pj1.habitList.db

import android.app.Application

class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()

        HabitRepo.initialize(this)
    }
}