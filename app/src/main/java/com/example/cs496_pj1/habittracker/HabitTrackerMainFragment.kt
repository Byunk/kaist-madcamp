package com.example.cs496_pj1.habittracker

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj1.contacts.UserContactEditActivity
import com.example.cs496_pj1.databinding.FragmentHabitTrackerMainBinding
import com.example.cs496_pj1.habittracker.database.Habit
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerMainFragment : Fragment() {

    private lateinit var binding: FragmentHabitTrackerMainBinding
    private lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private var habitList: List<Habit> = listOf()
    private val viewModel: HabitTrackerViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Observe Live Data
        viewModel.allWords.observe(viewLifecycleOwner) { habits ->
            habitList = habits
            var newAdapter = HabitTrackerMainAdapter(habitList)
            binding.rvHabitList.adapter = newAdapter
        }

        // Binding
        binding = FragmentHabitTrackerMainBinding.inflate(inflater, container, false)
        binding.rvHabitList.layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.VERTICAL, false)
        //binding.rvHabitList.adapter = HabitTrackerMainAdapter(viewModel.allWords)

        binding.mainCalendarButton.setText(SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(Date()).toString())

        childFragmentManager.setFragmentResultListener("dateRequestKey", viewLifecycleOwner) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val dateString = bundle.getString("date")!!
            // Do something with the result
            binding.mainCalendarButton.text = dateString
        }

        binding.mainCalendarButton.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(childFragmentManager, "DateDialog")
        }

        // Edit
        activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == RESULT_OK) {
                //val habit = result.data?.getStringExtra("habit") ?: ""
                //viewModel.insert(habit)
                val todo = result.data?.getStringExtra("todo") ?: ""
                val start = result.data?.getStringExtra("start") ?: ""
                val end = result.data?.getStringExtra("end") ?: ""

                if (todo != "" && start != "") {
                    if (end != "") {
                        val startLong = dateString2Date(start).time
                        val endLong = dateString2Date(end).time
                        viewModel.insert(Habit(todo, startLong, endLong, listOf()))
                    } else {
                        val startLong = dateString2Date(start).time
                        viewModel.insert(Habit(todo, startLong, null, listOf()))
                    }
                }
            }
        }

        binding.fabAdd.setOnClickListener {
            val intent = Intent(context, HabitTrackerEditActivity::class.java)
            activityResultLauncher.launch(intent)
        }

        return binding.root
    }

    private fun dateString2Date(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        return formatter.parse(dateString)
    }
}