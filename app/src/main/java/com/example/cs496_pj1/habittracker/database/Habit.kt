package com.example.cs496_pj1.habittracker.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson
import java.util.*
import kotlin.collections.ArrayList

@Entity(tableName = "habit_table")
data class Habit(
    @PrimaryKey @ColumnInfo(name = "todo") var todo: String,
    @ColumnInfo(name = "start") var start: Long,
    @ColumnInfo(name = "end") var end: Long?,
    @ColumnInfo(name = "did_date_array") var didDateArray: List<Long>,
    ) {
}

class Converters {
    @TypeConverter
    fun listToJson(value: List<Long>?) = Gson().toJson(value)

    @TypeConverter
    fun jsonToList(value: String) = Gson().fromJson(value, Array<Long>::class.java).toList()
}