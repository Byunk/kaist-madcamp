package com.example.cs496_pj2_ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj2_ui.databinding.ProfileDailyScheduleActivityBinding
import com.example.cs496_pj2_ui.service.RetrofitService
import com.example.cs496_pj2_ui.service.model.CustomCalendar
import com.example.cs496_pj2_ui.service.model.ScheduleData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDailyScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ProfileDailyScheduleActivityBinding

    private lateinit var adapter: ProfileDailyScheduleActivityAdapter

    private var schedules: ArrayList<ScheduleData> = arrayListOf()
    private lateinit var id: String
    private var year: Int = 0
    private var month: Int = 0
    private var date: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        id = intent.getStringExtra("id") as String
        year = intent.getIntExtra("year", -1)
        month = intent.getIntExtra("month", -1)
        date = intent.getIntExtra("date", -1)

        binding = ProfileDailyScheduleActivityBinding.inflate(layoutInflater)

        binding.fabAddDailySchedule.setOnClickListener {
            val intent = Intent(this, ProfileDailyScheduleAddActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        adapter = ProfileDailyScheduleActivityAdapter(this)
        binding.rvDailySchedule.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvDailySchedule.adapter = adapter
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()

        val call = RetrofitService.retrofitInterface.getUserDailySchedule(id, year, month, date)
        call.enqueue(object: Callback<ArrayList<ScheduleData>> {
            override fun onFailure(call: Call<ArrayList<ScheduleData>>, t: Throwable) {
                Log.e(RetrofitService.TAG, t.message + "in daily schedule")
            }

            override fun onResponse(
                call: Call<ArrayList<ScheduleData>>,
                response: Response<ArrayList<ScheduleData>>
            ) {
                if (response.body() != null) {
                    binding.tvEmptyDailySchedule.visibility = View.INVISIBLE
                    binding.rvDailySchedule.visibility = View.VISIBLE

                    schedules = response.body()!!
                    schedules.sortBy { it.time }
                    adapter.addDailySchedule(schedules)

                } else {
                    binding.tvEmptyDailySchedule.visibility = View.VISIBLE
                    binding.rvDailySchedule.visibility = View.INVISIBLE
                }
            }
        })
    }
}