package com.example.cs496_pj1.habitList.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HabitDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(dto: HabitDTO)

    @Query("select * from habit_Table")
    fun list(): LiveData<MutableList<HabitDTO>>

    @Query("select * from habit_Table where id = (:id)")
    fun selectOne(id: Long): HabitDTO

    @Update
    suspend fun update(dto: HabitDTO)

    @Delete
    fun delete(dto: HabitDTO)
}