package com.example.cs496_pj1.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.databinding.FragmentCalendarViewPagerBinding

class CalendarViewPagerFragment : Fragment() {

    private lateinit var viewPager: ViewPager2
    private lateinit var binding: FragmentCalendarViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCalendarViewPagerBinding.inflate(inflater, container, false)
        viewPager = binding.calendarPager
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val firstCalendarFragmentStateAdapter = FirstCalendarStateAdapter(requireActivity())
        viewPager.adapter = firstCalendarFragmentStateAdapter
        viewPager.orientation = ViewPager2.ORIENTATION_VERTICAL
        viewPager.setCurrentItem(
            firstCalendarFragmentStateAdapter.firstFragmentPosition, false)
    }

}