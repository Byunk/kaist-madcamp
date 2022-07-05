package com.example.cs496_pj1

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.example.cs496_pj1.contacts.ContactsFragment
import com.example.cs496_pj1.databinding.ActivityMainBinding
import com.example.cs496_pj1.gallery.GalleryFragment
import com.example.cs496_pj1.habittracker.HabitTrackerMainFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView
        // Connects Adapter To Pager
        viewPager = binding.pager
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
                R.id.ic_contacts -> {
                    viewPager.setCurrentItem(0)
                }
                R.id.ic_gallery -> {
                    viewPager.setCurrentItem(1)
                }
                R.id.ic_habittracker -> {
                    viewPager.setCurrentItem(2)
                }
            }
            true
        }

        // Check Whether User Permit him/her permission
        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        if (status == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "Permitted")
        } else {
            // Show Dialog
            ActivityCompat.requestPermissions(
                this,
                arrayOf<String>("android.permission.READ_CONTACTS"),
                100
            )
            Log.d("test", "Needed Permission")
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "permission granted")
        } else {
            Log.d("test", "permission denied")
        }
    }

    private inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) :
        FragmentStateAdapter(fm, lifecycle )
    {

        override fun getItemCount(): Int = NUM_PAGES

        // tab
        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ContactsFragment()
                1 -> return GalleryFragment()
                2 -> return HabitTrackerMainFragment()
            }
            return ContactsFragment()
        }
    }

}