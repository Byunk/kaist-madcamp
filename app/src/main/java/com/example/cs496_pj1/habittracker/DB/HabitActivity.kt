package com.example.cs496_pj1.habitList

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.SyncStateContract.Helpers.insert
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj1.databinding.ActivityHabitBinding
import com.example.cs496_pj1.habitList.db.HabitAdapter
import com.example.cs496_pj1.habitList.db.HabitDTO
import com.example.cs496_pj1.habitList.db.habitViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HabitActivity : AppCompatActivity() {
    lateinit var binding : ActivityHabitBinding
    lateinit var habitViewModel: habitViewModel
    lateinit var habitAdapter: HabitAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHabitBinding.inflate(layoutInflater)
        setContentView(binding.root)

        habitViewModel = ViewModelProvider(this)[habitViewModel::class.java]
        habitViewModel.habitList.observe(this) {
            habitAdapter.update(it)
        }
        habitAdapter = HabitAdapter(this)
        binding.rvHabitList.layoutManager = LinearLayoutManager(this)
        binding.rvHabitList.adapter = habitAdapter

        binding.fabAdd.setOnClickListener {
            val intent = Intent(this, EditHabitActivity::class.java).apply {
                putExtra("type", "ADD")
            }
            rqstActivity.launch(intent)
        }

        habitAdapter.setItemCheckBoxClickListener(object : HabitAdapter.ItemCheckBoxClickListener {
            override fun onClick(view: View, position: Int, itemId: Long) {
                CoroutineScope(Dispatchers.IO).launch {
                    val habit = habitViewModel.getOne(itemId)
                    habit.isChecked = !habit.isChecked
                    habitViewModel.update(habit)
                }
            }
        })
    }

    private val rqstActivity = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK) {
            val habit = it.data?.getSerializableExtra("habit") as HabitDTO

            when(it.data?.getIntExtra("flag", -1)) {
                0 -> {
                    CoroutineScope(Dispatchers.IO).launch {
                        habitViewModel.insert(habit)
                    }
                    Toast.makeText(this, "추가되었습니다.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}