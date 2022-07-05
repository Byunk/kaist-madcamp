package com.example.cs496_pj1.habittracker

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.cs496_pj1.habittracker.database.Habit
import com.example.cs496_pj1.habittracker.database.HabitRepository
import kotlinx.coroutines.launch

class HabitTrackerViewModel(private val repository: HabitRepository): ViewModel() {

    val allWords: LiveData<List<Habit>> by lazy {
        repository.allHabits
    }

    fun getHabitByTodo(todo: String): LiveData<Habit> {
        return repository.getHabitByTodo(todo)
    }

    fun insert(habit: Habit) = viewModelScope.launch {
        repository.insert(habit)
    }

    fun delete(todo: String) = viewModelScope.launch {
        repository.delete(todo)
    }

    fun deleteAll() = viewModelScope.launch {
        repository.deleteAll()
    }

}

class HabitViewModelFactory(private val repository: HabitRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HabitTrackerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return HabitTrackerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}