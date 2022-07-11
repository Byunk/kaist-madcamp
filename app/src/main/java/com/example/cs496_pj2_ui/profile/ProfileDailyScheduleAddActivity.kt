package com.example.cs496_pj2_ui.profile

import android.app.DatePickerDialog
import android.app.TimePickerDialog
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

        binding.btnCalendarAdd.setOnClickListener {
            val dateSetListener = DatePickerDialog.OnDateSetListener { datePicker, year, month, date ->
                val dateString = "${year}년 ${month+1}월 ${date}일"
                binding.tvDateAdd.text = dateString
            }
            DatePickerDialog(this, dateSetListener, today.get(Calendar.YEAR), today.get(Calendar.MONTH), today.get(Calendar.DAY_OF_MONTH)).show()
        }

        binding.btnTimeAdd.setOnClickListener {
            val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, time ->
                val timeString = "${hour}시 ${time}분"
                binding.tvTimeAdd.text = timeString
            }
            TimePickerDialog(this, timeSetListener, today.get(Calendar.HOUR_OF_DAY), today.get(Calendar.MINUTE), false).show()
        }

        binding.btnSaveAdd.setOnClickListener {

        }

        binding.btnCancleAdd.setOnClickListener {
            finish()
        }
    }
}