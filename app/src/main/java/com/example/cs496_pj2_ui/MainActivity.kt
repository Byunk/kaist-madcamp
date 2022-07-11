package com.example.cs496_pj2_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.cs496_pj2_ui.databinding.MainActivityBinding
import com.example.cs496_pj2_ui.profile.ProfileMainFragment
import com.example.cs496_pj2_ui.service.SocketService

private const val NUM_PAGES = 2

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id")!!

        // Connects Socket to Server
        SocketService.setSocket()
        SocketService.establishConnection()

        // Pager Config
        binding.pagerMain.adapter = PagerAdapter(supportFragmentManager, lifecycle)

        // Navigation Bar Config
        binding.navMain.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.page_profile -> {
                    binding.pagerMain.setCurrentItem(0)
                    true
                }
                R.id.page_chat -> {
                    binding.pagerMain.setCurrentItem(1)
                    true
                }
            }
            false
        }
    }

    inner class PagerAdapter(fm: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(fm, lifecycle) {
        override fun createFragment(position: Int): Fragment {
            val bundle: Bundle = Bundle()
            bundle.putString("id", id)

            when (position) {
                0 -> {
                    val profileMainFragment = ProfileMainFragment()
                    profileMainFragment.arguments = bundle
                    return profileMainFragment
                }

                else -> {
                    val profileMainFragment = ProfileMainFragment()
                    profileMainFragment.arguments = bundle
                    return profileMainFragment
                }
            }
        }

        override fun getItemCount(): Int {
            return NUM_PAGES
        }
    }
}