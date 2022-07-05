package com.example.cs496_pj1.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cs496_pj1.databinding.ActivityHabitTrackerAddBinding
import com.example.cs496_pj1.databinding.ActivityHabitTrackerEditBinding
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerAddActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitTrackerAddBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitTrackerAddBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnStart = binding.habitStart
        val btnEnd = binding.habitEnd
        val tvStart = binding.habitStartTv
        val tvEnd = binding.habitEndTv
        val habitText = binding.habitTodo

        val btnSave = binding.saveButton
        val btnCancel = binding.cancelButton

        bindDialog(btnStart, tvStart)
        bindDialog(btnEnd, tvEnd)

        // init start date
        val todayString = SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
            Date()).toString()
        tvStart.text = todayString

        //save
        btnSave.setOnClickListener {
            val start = tvStart.text as String
            val end = tvEnd.text as String
            val todo = habitText.text.toString()

            val intent = Intent(this, HabitTrackerMainFragment::class.java).apply {
                putExtra("todo", todo)
                putExtra("start", start)
                putExtra("end", end)
            }
            setResult(RESULT_OK, intent)
            if (!isFinishing) finish()
        }

        btnCancel.setOnClickListener {
            finish()
        }
    }

    private fun bindDialog(btn: Button, tv: TextView) {

        supportFragmentManager.setFragmentResultListener("dateRequestKey", this) { requestKey, bundle ->
            val dateString = bundle.getString("date")!!
            tv.text = dateString
        }

        btn.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(supportFragmentManager, "DateDialog")
        }

    }
}