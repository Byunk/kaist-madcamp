package com.example.cs496_pj1.habitList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs496_pj1.databinding.ActivityEditHabitBinding
import com.example.cs496_pj1.habitList.db.HabitDTO
import java.text.SimpleDateFormat

class EditHabitActivity : AppCompatActivity() {
    lateinit var binding: ActivityEditHabitBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // data query
        val type = intent.getStringExtra("type")

        if (type.equals("ADD")) {
            binding.btnSave.text = "추가하기"
        }else{
            binding.btnSave.text = "수정하기"
        }

        // Events
        binding.btnSave.setOnClickListener {
            val title = binding.etHabitTitle.text.toString()
            val content = binding.etHabitContent.text.toString()
            val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm").format(System.currentTimeMillis())

            if (type.equals("ADD")) {
                if (title.isNotEmpty() && content.isNotEmpty()) {
                    val habit = HabitDTO(0, title, content, currentDate, false)
                    val intent = Intent().apply {
                        putExtra("habit", habit)
                        putExtra("flag", 0) // flag 0 for "ADD"
                    }
                    setResult(RESULT_OK, intent)
                    finish()
                }
            } else {
                // 수정
            }
        }
    }
}