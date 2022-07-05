package com.example.cs496_pj1.habitList.db

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class habitViewModel: ViewModel() {
    val habitList: LiveData<MutableList<HabitDTO>>
    private val habitRepositoy: HabitRepo = HabitRepo.get()

    init {
        habitList = habitRepositoy.list()
    }

    fun getOne(id: Long) = habitRepositoy.getItem(id)

    fun insert(dto: HabitDTO) = viewModelScope.launch(Dispatchers.IO) {
        habitRepositoy.insert(dto)
    }

    fun update(dto: HabitDTO) = viewModelScope.launch(Dispatchers.IO) {
        habitRepositoy.update(dto)
    }

    fun delete(dto: HabitDTO) = viewModelScope.launch(Dispatchers.IO) {
        habitRepositoy.delete(dto)
    }

}