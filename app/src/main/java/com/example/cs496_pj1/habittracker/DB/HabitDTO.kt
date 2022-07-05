package com.example.cs496_pj1.habitList.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "habit_Table")
class HabitDTO(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "content") var content: String,
    @ColumnInfo(name = "timestamp") val timestamp: String,
    @ColumnInfo(name = "isChecked") var isChecked: Boolean

    ): Serializable {    //for intent
}