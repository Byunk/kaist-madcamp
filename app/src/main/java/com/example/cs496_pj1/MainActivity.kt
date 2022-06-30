package com.example.cs496_pj1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import java.util.ArrayList


private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private val tabTitleArray = arrayOf(
        "Contacts",
        "Gallery",
        "Undecided"
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        //setContentView(R.layout.activity_main)
        setContentView(binding.root)

        viewPager = findViewById(R.id.pager)
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter

        val tabLayout = binding.tabs

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()

        setUpTap()
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle )
    {

        override fun getItemCount(): Int = NUM_PAGES

        private val mFragmentList = ArrayList<Fragment>()
        private val mFragmentTitleList = ArrayList<String>()

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ContactsFragment()
                1 -> return GalleryFragment()
                2 -> return tmp()
            }
            return ContactsFragment()
        }

//        fun getItem(position: Int): Fragment {
//            return mFragmentList[position]
//        }
//
//        fun getPageTitle(position: Int): CharSequence? {
//            return mFragmentTitleList[position]
//        }
//
//        fun addFragment(fragment: Fragment, title: String){
//            mFragmentList.add(fragment)
//        }

    }

    private fun setUpTap(){
        val adapter = ViewPagerAdapter(supportFragmentManager,lifecycle )
        adapter.createFragment(0)
        adapter.createFragment(1)
        adapter.createFragment(2)
        binding.pager.adapter = adapter

        TabLayoutMediator(binding.tabs, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()



        binding.tabs.getTabAt(0)!!.setIcon(R.drawable.ic_baseline_contact_phone_24)
        binding.tabs.getTabAt(1)!!.setIcon(R.drawable.ic_baseline_photo_library_24)
        binding.tabs.getTabAt(2)!!.setIcon(R.drawable.ic_baseline_assignment_24)


    }
}