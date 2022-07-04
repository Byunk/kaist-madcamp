package com.example.cs496_pj1.habittracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.ActivityCalendarDetailBinding
import com.example.cs496_pj1.models.Habit
import java.text.SimpleDateFormat
import java.util.*

class CalendarDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCalendarDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalendarDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.exitFromHabitButton.setOnClickListener {
            finish()
        }

        val date = intent.getStringExtra("date")
        binding.dateText.setText(date)
    }

    override fun onResume() {
        super.onResume()

        // Fetch HabitList

        // Temp Data
        val habitList = arrayListOf<Habit>(Habit("Feeding Dodam", Date(), Date()))

        binding.rvHabitList.layoutManager = LinearLayoutManager(baseContext, LinearLayoutManager.VERTICAL, false)
        //binding.rvHabitList.setHasFixedSize(true)
        binding.rvHabitList.adapter = CalendarDetailAdapter(habitList)
    }
}