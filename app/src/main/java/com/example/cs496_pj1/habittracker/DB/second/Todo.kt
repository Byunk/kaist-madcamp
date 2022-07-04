package com.example.cs496_pj1.habittracker.DB.second

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_db")
class Todo (@PrimaryKey(autoGenerate = true) var id: Long?,
            @ColumnInfo(name = "title") var title: String?,
            @ColumnInfo(name = "timestamp") var timestamp: String?,
            @ColumnInfo(name = "isChecked") var isChecked: Boolean
            ){
    constructor(): this(null,"", "",false)
}