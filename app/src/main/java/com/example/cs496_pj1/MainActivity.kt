package com.example.cs496_pj1

import    android.content.pm.PackageManager
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
import com.example.cs496_pj1.habittracker.CalendarViewPagerFragment
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

//        //fragments
//        val contactsFragment = ContactsFragment()
//        val galleryFragment = GalleryFragment()
//        //  val calendarFragment = CalendarFragment()
//
//        makeCurrentFragment(contactsFragment)
//        navView.setOnItemSelectedListener {
//            when(it.itemId){
//                R.id.ic_contacts -> makeCurrentFragment(contactsFragment)
//                R.id.ic_gallery -> makeCurrentFragment(galleryFragment)
//                //R.id.ic_habittracker -> makeCurrentFragment(calendarFragment)
//            }
//            true
//
//        }

//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
//
//        // navigation Controller
//        navController = navHostFragment.navController
//        setupWithNavController(navView, navController)
//
//        val appBarConfiguration = AppBarConfiguration(
//            setOf(
//                R.id.navigation_tap1, R.id.navigation_tap2, R.id.navigation_tap3
//            )
//        )
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

        // Connects Adapter To Pager
        viewPager = findViewById(R.id.pager)
        val pagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)
        viewPager.adapter = pagerAdapter

        // slide 시 nav 탭 함께 변경
        binding.pager.registerOnPageChangeCallback(
            object : ViewPager2.OnPageChangeCallback(){

                override fun onPageSelected(position: Int){
                    super.onPageSelected(position)
                    navView.menu.getItem(position).isChecked = true
                }
            }
        )
        // 리스너 연결
        navView.setOnItemSelectedListener(){
            true
        }

        // Check Whether User Permit him/her permission
        val status = ContextCompat.checkSelfPermission(this, "android.permission.READ_CONTACTS")
        if (status == PackageManager.PERMISSION_GRANTED) {
            Log.d("test", "Permitted")
        } else {
            // Show Dialog
            ActivityCompat.requestPermissions(this, arrayOf<String>("android.permission.READ_CONTACTS"), 100)
            Log.d("test","Needed Permission")
        }

    }

//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.frag_layout, fragment)
//            commit()
//        }

//
//
//    public fun onNavigationItemSelected(item: MenuItem): Boolean{
//        when(item.itemId){
//            R.id.ic_contacts -> {
//                // 페이저 현재 item에 n-th 화면 대입
//                binding.pager.currentItem = 0
//                return true
//            }
//            R.id.ic_gallery -> {
//                binding.pager.currentItem = 1
//                return true
//            }
//            androidx.core.R.id.icon -> {
//                binding.pager.currentItem = 2
//                return true
//            }else -> return false
//        }
//    }
//
//

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

//        private val mFragmentList = ArrayList<Fragment>()
//        private val mFragmentTitleList = ArrayList<String>()

        override fun createFragment(position: Int): Fragment {
            when (position) {
                0 -> return ContactsFragment()
                1 -> return GalleryFragment()
                2 -> return CalendarViewPagerFragment()
            }
            return ContactsFragment()
        }
    }
}