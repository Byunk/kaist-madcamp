package com.example.cs496_pj1.habittracker

import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.annotation.RequiresApi
import androidx.core.os.bundleOf
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentHabitTrackerMainCalendarBinding
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerMainCalendarFragment : DialogFragment() {

    private lateinit var binding: FragmentHabitTrackerMainCalendarBinding
    private var dateString = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHabitTrackerMainCalendarBinding.inflate(inflater, container, false)

        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val datePicker = binding.mainDatepicker
        val cancelBtn = binding.mainCalendarCancelButton
        val saveBtn = binding.mainCalendarSaveButton


        datePicker.setOnDateChangedListener { datePicker, year, month, day ->
            dateString = "${year}년 ${month+1}월 ${day}일"
        }

        cancelBtn.setOnClickListener {
            dismiss()
        }

        saveBtn.setOnClickListener {
            setFragmentResult("dateRequestKey", bundleOf("date" to dateString))
            dismiss()
        }
    }



}