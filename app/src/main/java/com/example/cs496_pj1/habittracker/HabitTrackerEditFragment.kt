package com.example.cs496_pj1.habittracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import com.example.cs496_pj1.databinding.FragmentHabitTrackerEditBinding
import com.example.cs496_pj1.habittracker.database.Habit
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerEditFragment : Fragment() {

    private val viewModel: HabitTrackerViewModel by activityViewModels()
    private lateinit var binding: FragmentHabitTrackerEditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHabitTrackerEditBinding.inflate(inflater, container, false)

        val btnStart = binding.habitStart
        val btnEnd = binding.habitEnd
        val tvStart = binding.habitStartTv
        val tvEnd = binding.habitEndTv
        val habitText = binding.habitTodo

        val btnSave = binding.saveButton
        val btnCancel = binding.cancelButton

        bindDialog(btnStart, tvStart)
        bindDialog(btnEnd, tvEnd)

        btnStart.setText(
            SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
                Date()
            ).toString())

        btnSave.setOnClickListener {
            val start = dateString2Date(tvStart.text as String).time
            val end = tvEnd.text
            val todo = habitText.toString()

            if (end != "") {
                val habit = Habit(todo, start, dateString2Date(end as String).time, listOf())
                viewModel.insert(habit)
            } else {
                val habit = Habit(todo, start, null, listOf())
                viewModel.insert(habit)
            }
        }

        btnCancel.setOnClickListener {
            activity?.onBackPressed()
        }

        return binding.root
    }

    private fun bindDialog(btn: Button, tv: TextView) {

        childFragmentManager.setFragmentResultListener("dateRequestKey", viewLifecycleOwner) { requestKey, bundle ->
            // We use a String here, but any type that can be put in a Bundle is supported
            val dateString = bundle.getString("date")!!
            // Do something with the result
            tv.text = dateString
        }

        btn.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(childFragmentManager, "DateDialog")
        }

    }

    private fun dateString2Date(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        return formatter.parse(dateString)
    }

}