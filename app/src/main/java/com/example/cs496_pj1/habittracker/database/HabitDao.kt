package com.example.cs496_pj1.habittracker.database

import androidx.lifecycle.LiveData
import androidx.room.*

//TODO: Find habit whose Date include specific date
//TODO: Whether user checked habit in specific date

@Dao
interface HabitDao {
    @Query("SELECT * FROM habit_table")
    fun getAll(): LiveData<List<Habit>>

    @Query("SELECT * FROM habit_table WHERE todo LIKE :todo")
    fun findByTodo(todo: String): LiveData<Habit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg habit: Habit)

    @Query("DELETE FROM habit_table WHERE todo LIKE :todo")
    fun deleteByTodo(todo: String)

    @Query("DELETE FROM habit_table")
    fun deleteAll()
}