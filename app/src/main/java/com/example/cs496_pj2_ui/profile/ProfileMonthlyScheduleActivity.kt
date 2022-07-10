package com.example.cs496_pj2_ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj2_ui.databinding.ProfileMonthlyScheduleActivityBinding

class ProfileMonthlyScheduleActivity : AppCompatActivity() {

    private lateinit var binding: ProfileMonthlyScheduleActivityBinding
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileMonthlyScheduleActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id")!!

        val pager = binding.pagerCalendar
        val adapter = ProfileMonthlyScheduleAdapter(this)
        pager.adapter = adapter
        pager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        pager.setCurrentItem(adapter.firstFragmentPosition, false)
    }

    inner class ProfileMonthlyScheduleAdapter(fm: FragmentActivity): FragmentStateAdapter(fm) {
        val firstFragmentPosition = Int.MAX_VALUE / 2

        override fun getItemCount(): Int = Int.MAX_VALUE

        override fun createFragment(position: Int): Fragment {
            var bundle = Bundle()
            bundle.putInt("position", position)
            bundle.putString("id", id)
            val profileMonthlyScheduleFragment = ProfileMonthlyScheduleFragment()
            profileMonthlyScheduleFragment.arguments = bundle
            return profileMonthlyScheduleFragment
        }
    }
}
