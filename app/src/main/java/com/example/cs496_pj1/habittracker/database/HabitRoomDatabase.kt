package com.example.cs496_pj1.habittracker.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

@Database(entities = [Habit::class], version = 1)
@TypeConverters(Converters::class)
abstract class HabitRoomDatabase: RoomDatabase() {
    abstract fun habitDao(): HabitDao

    companion object {
        @Volatile
        private var INSTANCE: HabitRoomDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): HabitRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HabitRoomDatabase::class.java,
                    "habit_database"
                ).addCallback(HabitDatabaseCallback(scope)).build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class HabitDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.habitDao())
                }
            }
        }

        suspend fun populateDatabase(habitDao: HabitDao) {
            // Delete all content here.
            habitDao.deleteAll()

            val cal = Calendar.getInstance()

            val start1 = cal.run {
                set(2022, 6, 20)
                time
            }

            val start2 = cal.run {
                set(2022, 5, 20)
                time
            }

            val end1 = cal.run {
                set(2022, 7, 11)
                time
            }

            val end2 = cal.run {
                set(2022, 7, 20)
                time
            }

            // Add sample Data.
            var habit = Habit("도담이 밥주기", start1.time, end1.time, arrayListOf<Long>())
            habitDao.insert(habit)
            habit = Habit("술 마시기", start2.time, null, arrayListOf<Long>())
            habitDao.insert(habit)

            // TODO: Add your own words!
        }
    }
}