package com.example.cs496_pj1.habittracker.database

import android.app.Application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class HabitApplication: Application() {
    val applicationScope = CoroutineScope(SupervisorJob())

    val database by lazy { HabitRoomDatabase.getDatabase(this, applicationScope)}
    val repository by lazy { HabitRepository(database.habitDao()) }

}