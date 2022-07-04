package com.example.cs496_pj1.habittracker

import androidx.room.*

@Dao
interface TodoDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(todo: Todo) //entity insert

    @Query("SELECT * FROM todo_db")
    fun getAll(): List<Todo>

    @Query("SELECT * FROM todo_db where title = (:title)")
    fun select(title: String): Todo

    @Delete
    fun delete(todo: Todo)

}