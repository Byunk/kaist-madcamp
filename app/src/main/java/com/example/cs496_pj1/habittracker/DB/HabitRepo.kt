package com.example.cs496_pj1.habitList.db

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room

private const val DATABASE_NAME = "habit-database.db"
class HabitRepo private constructor(context: Context){

    private val database: HabitDataBase = Room.databaseBuilder(
        context.applicationContext,
        HabitDataBase::class.java,
        DATABASE_NAME
    ).build()

    companion object {
        private var INSTANCE: HabitRepo?=null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = HabitRepo(context)
            }
        }

        fun get(): HabitRepo {
            return INSTANCE ?:
            throw IllegalStateException("Repository must be initialized")
        }
    }

    private val habitDao = database.habitDao()

    //funciton
    fun insert(dto: HabitDTO) = habitDao.insert(dto)

    suspend fun update(dto: HabitDTO) = habitDao.update(dto)

    fun list(): LiveData<MutableList<HabitDTO>> = habitDao.list()

    fun getItem(id: Long): HabitDTO = habitDao.selectOne(id)

    fun delete(dto: HabitDTO) = habitDao.delete(dto)

}
