package com.example.cs496_pj1.habittracker

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cs496_pj1.models.Habit
import com.example.cs496_pj1.models.createSampleHabit

class HabitTrackerViewModel: ViewModel() {

    private val dummyHabits = createSampleHabit()
    var habits = MutableLiveData<List<Habit>>(dummyHabits)

    fun find(todo: String): Habit? {
        val habit = habits.value?.find {
            it.todo == todo
        }
        return habit
    }
}