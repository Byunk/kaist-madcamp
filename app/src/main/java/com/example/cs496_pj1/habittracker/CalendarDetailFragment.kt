package com.example.cs496_pj1.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentCalendarDetailBinding

class CalendarDetailFragment : Fragment() {

    private lateinit var binding: FragmentCalendarDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalendarDetailBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        val year = intent.getStringExtra("year")
        val month = intent.getStringExtra("month")
        val date = intent.getStringExtra("date")
    }
}