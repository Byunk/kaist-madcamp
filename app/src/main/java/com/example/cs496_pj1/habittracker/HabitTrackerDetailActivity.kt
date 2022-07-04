package com.example.cs496_pj1.habittracker

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
import com.google.android.material.bottomnavigation.BottomNavigationView

class HabitTrackerDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHabitTrackerDetailBinding
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHabitTrackerDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.habitTrackerNavView

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
                R.id.ic_statistic1 -> {
                    viewPager.setCurrentItem(1)
                }
                R.id.ic_statistic2 -> {
                    viewPager.setCurrentItem(2)
                }
            }
            true
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle)
    {

        override fun getItemCount(): Int = 3

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return CalendarMainFragment()
                //1 -> return GalleryFragment()
                //2 -> return HabitTrackerMainFragment()
            }
            return CalendarMainFragment()
        }
    }
}