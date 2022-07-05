package com.example.cs496_pj1.habittracker.calendar

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cs496_pj1.models.Habit
import java.util.*
import kotlin.collections.ArrayList

class FirstCalendarStateAdapter(fragmentActivity: FragmentActivity, start: Long, end: Long,
didArrayList: ArrayList<Date>)
    : FragmentStateAdapter(fragmentActivity) {

    val firstFragmentPosition = Int.MAX_VALUE / 2
    var start = start
    var end = end
    var didArrayList = didArrayList

    override fun getItemCount(): Int = Int.MAX_VALUE

    override fun createFragment(position: Int): Fragment {
        return CalendarFragment(position, start, end, didArrayList)
    }

}