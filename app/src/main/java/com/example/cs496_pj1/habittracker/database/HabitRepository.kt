package com.example.cs496_pj1.habittracker.database

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import java.util.*

class HabitRepository(private val habitDao: HabitDao) {

    val allHabits: LiveData<List<Habit>> = habitDao.getAll()

    fun getHabitByTodo(todo: String): LiveData<Habit> {
        return habitDao.findByTodo(todo)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(habit: Habit) {
        habitDao.insert(habit)
    }

    suspend fun delete(todo: String) {
        habitDao.deleteByTodo(todo)
    }

    suspend fun deleteAll() {
        habitDao.deleteAll()
    }

}