package com.example.cs496_pj1.habittracker

import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.R
import com.example.cs496_pj1.contacts.ContactsFragment
import com.example.cs496_pj1.databinding.ActivityHabitTrackerDetailBinding
import com.example.cs496_pj1.gallery.GalleryFragment
import com.example.cs496_pj1.habittracker.calendar.CalendarMainFragment
import com.example.cs496_pj1.habittracker.charts.PieChartFragment
import com.example.cs496_pj1.models.Habit
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.*
import kotlin.collections.ArrayList

class HabitTrackerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitTrackerDetailBinding
    private lateinit var viewPager: ViewPager2
    var start: Long = 0
    var end: Long = 0
    var didArray: ArrayList<Date> = arrayListOf()

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHabitTrackerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.habitTrackerNavView

        val default = Calendar.getInstance().time.time
        start = intent.getLongExtra("start", default)
        end = intent.getLongExtra("end", default)
        didArray = intent.getSerializableExtra("didArray") as ArrayList<Date>

        // Connects Adapter To Pager
        viewPager = binding.habitTrackerPager
        viewPager.adapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        // slide 시 nav 탭 함께 변경
        viewPager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int){
                    super.onPageSelected(position)
                    navView.menu.getItem(position).isChecked = true
                }
            }
        )
        // 리스너 연결
        navView.setOnItemSelectedListener() { item ->
            //true
            when(item.itemId) {
                R.id.ic_calendar -> {
                    viewPager.setCurrentItem(0)
                }
                R.id.ic_statistic -> {
                    viewPager.setCurrentItem(1)
                }
            }
            true
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle)
    {

        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return CalendarMainFragment(start, end, didArray)
                1 -> return PieChartFragment(start, end, didArray)
            }
            return CalendarMainFragment(start, end, didArray)
        }
    }
}