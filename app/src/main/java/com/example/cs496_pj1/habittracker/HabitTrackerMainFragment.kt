package com.example.cs496_pj1.habittracker

import android.content.Context
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj1.databinding.FragmentHabitTrackerMainBinding
import com.example.cs496_pj1.models.createSampleHabit
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerMainFragment : Fragment() {

    private lateinit var binding: FragmentHabitTrackerMainBinding
    private lateinit var mContext: Context


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHabitTrackerMainBinding.inflate(inflater, container, false)
        binding.rvHabitList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvHabitList.adapter = HabitTrackerMainAdapter(createSampleHabit())

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