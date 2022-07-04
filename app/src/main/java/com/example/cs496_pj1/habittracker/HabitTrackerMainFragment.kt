package com.example.cs496_pj1.habittracker

import android.app.DatePickerDialog
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.contacts.ContactsFragment
import com.example.cs496_pj1.databinding.FragmentHabitTrackerMainBinding
import com.example.cs496_pj1.gallery.GalleryFragment
import com.example.cs496_pj1.habittracker.calendar.CalendarViewPagerFragment
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import java.util.logging.Level.parse

class HabitTrackerMainFragment : Fragment() {

    private lateinit var binding: FragmentHabitTrackerMainBinding
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHabitTrackerMainBinding.inflate(inflater, container, false)

        binding.mainCalendarButton.setText(SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(Date()).toString())

        childFragmentManager.setFragmentResultListener("dateRequestKey", viewLifecycleOwner) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val dateString = bundle.getString("date")!!
            // Do something with the result
            binding.mainCalendarButton.text = dateString
        }

        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        mContext = context
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onResume() {
        super.onResume()

        binding.mainCalendarButton.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(childFragmentManager, "DateDialog")
        }

        // View pager for dates / Edit button


    }


}