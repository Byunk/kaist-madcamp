package com.example.cs496_pj1.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs496_pj1.databinding.ActivityHabitTrackerAddBinding

class HabitTrackerEditActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHabitTrackerAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHabitTrackerAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}