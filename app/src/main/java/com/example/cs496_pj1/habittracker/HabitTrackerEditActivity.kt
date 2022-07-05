package com.example.cs496_pj1.habittracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.cs496_pj1.databinding.ActivityHabitTrackerEditBinding
import java.text.SimpleDateFormat
import java.util.*

class HabitTrackerEditActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitTrackerEditBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitTrackerEditBinding.inflate(layoutInflater)
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
        btnStart.setText(
            SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(
                Date()
            ).toString())

        //save
        btnSave.setOnClickListener {
            val start = tvStart.text as String
            val end = tvEnd.text as String
            val todo = habitText.toString()

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
            // We use a String here, but any type that can be put in a Bundle is supported
            val dateString = bundle.getString("date")!!
            // Do something with the result
            tv.text = dateString
        }

        btn.setOnClickListener {
            val dialog = HabitTrackerMainCalendarFragment()
            dialog.show(supportFragmentManager, "DateDialog")
        }

    }
}