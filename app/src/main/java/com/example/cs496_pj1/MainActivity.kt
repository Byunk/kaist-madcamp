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
import com.example.cs496_pj1.models.Contacts
import com.google.android.material.tabs.TabLayoutMediator


private const val NUM_PAGES = 3

class MainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var binding: ActivityMainBinding
    private val tabTitleArray = arrayOf(
        "Contacts",
        "Gallery",
        "Undecided"
    )
    private var contactsList = ArrayList<Contacts>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        // Check Whether User Permit him/her permission
        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        if (status == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "Permitted")
        } else {
            // Show Dialog
            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.READ_CONTACTS"), 100)
            Log.d("test","Needed Permission")
        }

        viewPager = findViewById(R.id.pager)
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter


        val tabLayout = binding.tabs

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = tabTitleArray[position]
        }.attach()


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

    private inner class ViewPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {

        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ContactsFragment()
                1 -> return GalleryFragment()
            }
            return ContactsFragment()
        }
    }
}