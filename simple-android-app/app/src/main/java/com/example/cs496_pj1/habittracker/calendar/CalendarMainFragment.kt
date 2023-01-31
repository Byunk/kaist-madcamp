package com.example.cs496_pj1.habittracker.calendar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.databinding.FragmentCalendarMainBinding
import com.example.cs496_pj1.models.Habit
import java.util.*
import kotlin.collections.ArrayList

class CalendarMainFragment(start: Long, end: Long, didArrayList: ArrayList<Date>) : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var binding: FragmentCalendarMainBinding
    var start = start
    var end = end
    var didArrayList = didArrayList

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalendarMainBinding.inflate(inflater, container, false)
        viewPager = binding.calendarPager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstCalendarFragmentStateAdapter = FirstCalendarStateAdapter(requireActivity(), start, end,
        didArrayList)
        viewPager.adapter = firstCalendarFragmentStateAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.setCurrentItem(
            firstCalendarFragmentStateAdapter.firstFragmentPosition, false)
    }

}