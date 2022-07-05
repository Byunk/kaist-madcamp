package com.example.cs496_pj1.habittracker

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj1.databinding.FragmentHabitTrackerMainBinding
import com.example.cs496_pj1.models.Habit
import com.example.cs496_pj1.models.createSampleHabit
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerMainFragment : Fragment() {

    private lateinit var binding: FragmentHabitTrackerMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var habitArray = createSampleHabit()
    private var date: String = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(Date()).toString()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Binding
        binding = FragmentHabitTrackerMainBinding.inflate(inflater, container, false)
        binding.rvHabitList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        binding.rvHabitList.adapter = HabitTrackerMainAdapter(habitArray, date)

        // Set initial Value to Today
        binding.mainCalendarButton.setText(date)

        // Edit Button
        binding.editButton.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.rvHabitList.adapter = HabitTrackerMainAdapterForEdit(habitArray, date)
            } else {
                binding.rvHabitList.adapter = HabitTrackerMainAdapter(habitArray, date)
            }
        }

        // Mini Calendar Config
        childFragmentManager.setFragmentResultListener("dateRequestKey", viewLifecycleOwner) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val dateString = bundle.getString("date")!!
            // Do something with the result
            binding.mainCalendarButton.text = dateString
            date = dateString
            val newAdapter = HabitTrackerMainAdapter(habitArray, date)
            binding.rvHabitList.adapter = newAdapter
        }

        binding.mainCalendarButton.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(childFragmentManager, "DateDialog")
        }

        // Add Logic
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                val todo = result.data?.getStringExtra("todo") ?: ""
                val start = result.data?.getStringExtra("start") ?: ""
                val end = result.data?.getStringExtra("end") ?: ""

                // Valid Check
                if (todo != "" && start != "") {
                    if (end != "") {
                        val startDate = dateString2Date(start)
                        val endDate = dateString2Date(end)
                        if (!endDate.before(startDate)) {
                            habitArray.add(Habit(todo, startDate, endDate))
                        }
                    } else {
                        val startDate = dateString2Date(start)
                        habitArray.add(Habit(todo, startDate, null))
                    }
                    val newAdapter = HabitTrackerMainAdapter(habitArray, date)
                    binding.rvHabitList.adapter = newAdapter
                }
            }
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(context, HabitTrackerAddActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        return binding.root
    }

    private fun dateString2Date(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        return formatter.parse(dateString)
    }
}