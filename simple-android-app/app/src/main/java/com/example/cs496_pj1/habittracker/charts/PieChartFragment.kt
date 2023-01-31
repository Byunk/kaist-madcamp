package com.example.cs496_pj1.habittracker.charts

import android.graphics.Color
import android.graphics.Typeface
import android.graphics.fonts.Font
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentPieChartBinding
import com.example.cs496_pj1.models.Habit
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import java.util.*
import kotlin.collections.ArrayList

class PieChartFragment(start: Long, end: Long, didArrayList: ArrayList<Date>) : Fragment() {

    private lateinit var binding: FragmentPieChartBinding
    var start = start
    var end = end
    var didArrayList = didArrayList

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
        val array: ArrayList<PieEntry> = arrayListOf()

        val diff = end - start
        val diffDates = diff / (24 * 60 * 60 * 1000)
        Log.i("Main", diffDates.toString())

        val doDates = diffDates - didArrayList.size

        array.add(PieEntry(doDates.toFloat(), "You Did it!"))
        array.add(PieEntry((diffDates - doDates).toFloat(), "You are Lazy..."))

        pieChart.setUsePercentValues(true)
        pieChart.setDrawHoleEnabled(false)
        pieChart.setDragDecelerationFrictionCoef(0.95f)


        val description = Description()
        description.setText("성취도")
        pieChart.setDescription(description)
        val dataset = PieDataSet(array, "Habit Tracker")
        dataset.setSliceSpace(3f)
        dataset.setColors(Color.BLUE, Color.RED)

        val data = PieData((dataset))
        data.setValueTextSize(20f)
        data.setValueTextColor(Color.WHITE)
        pieChart.data = data

    }

}