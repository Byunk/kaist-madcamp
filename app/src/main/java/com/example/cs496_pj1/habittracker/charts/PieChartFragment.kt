package com.example.cs496_pj1.habittracker.charts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentPieChartBinding
import com.example.cs496_pj1.models.Habit
import java.util.*
import kotlin.collections.ArrayList

class PieChartFragment(didArrayList: ArrayList<Date>) : Fragment() {

    private lateinit var binding: FragmentPieChartBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPieChartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pieChart = binding.pieChart

    }

}