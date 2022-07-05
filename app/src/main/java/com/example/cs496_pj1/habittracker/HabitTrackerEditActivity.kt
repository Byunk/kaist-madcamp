package com.example.cs496_pj1.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs496_pj1.databinding.ActivityHabitTrackerEditBinding

class HabitTrackerEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitTrackerEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHabitTrackerEditBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}