package com.example.cs496_pj2_ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.ProfileDailyScheduleAddActivityBinding
import java.util.*

class ProfileDailyScheduleAddActivity : AppCompatActivity() {

    lateinit var binding: ProfileDailyScheduleAddActivityBinding
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        id = intent.getStringExtra("id")!!

        binding = ProfileDailyScheduleAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val today = Calendar.getInstance()

        val datePicker = findViewById<DatePicker>(R.id.add_date_picker)
        binding.btnCalendarAdd.setOnClickListener {
            datePicker.init(
                today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)
            ) { _, year, month, day ->
                val dateString = "${year}년 ${month+1}월 ${day}일"
                binding.tvDateAdd.text = dateString
            }
        }

        val timePicker = findViewById<TimePicker>(R.id.add_time_picker)
        binding.btnTimeAdd.setOnClickListener {
            timePicker.setOnTimeChangedListener { picker, hour, minute ->
                val timeString = "${hour}시 ${minute}분"
                binding.tvTimeAdd.text = timeString
            }
        }

        binding.btnSaveAdd.setOnClickListener {

        }

        binding.btnCancleAdd.setOnClickListener {
            finish()
        }
    }
}