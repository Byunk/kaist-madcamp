package com.example.cs496_pj1.habitList.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(HabitDTO::class), version = 1)
abstract class HabitDataBase: RoomDatabase() {
    abstract fun habitDao(): HabitDAO
}
