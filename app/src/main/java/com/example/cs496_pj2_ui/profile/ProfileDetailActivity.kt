package com.example.cs496_pj2_ui.profile

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.ProfileDetailActivityBinding
import com.example.cs496_pj2_ui.databinding.ProfileMonthlyScheduleActivityBinding
import com.example.cs496_pj2_ui.service.RetrofitService
import com.example.cs496_pj2_ui.service.model.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var binding: ProfileDetailActivityBinding
    private lateinit var id: String
    private lateinit var data: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getParcelableExtra("data")!!

        //if intent does not have id, it is myinfo
        id = intent.getStringExtra("id") ?: data.id

        binding.tvNameProfileDetail.text = data.name
        binding.tvStatusProfileDetail.text = data.status ?: ""

        if (data.imgUrl == null) {
            binding.imgProfileDetail.setImageResource(R.drawable.account)
        } else {
            Glide.with(this)
                .load(data.imgUrl)
                .into(binding.imgProfileDetail)
        }

        binding.tvFood.text = data.food?.let {
            it + "를 먹고싶어요!"
        } ?: ""
        binding.tvHobby.text = data.hobby?.let {
            it + "를 좋아해요!"
        } ?: ""
        binding.tvFavorites.text = data.favorites?.let {
            it + "에 관심있어요!"
        } ?: ""
        binding.tvWeekend.text = data.weekend?.let {
            it + "를 하는 편이에요!"
        } ?: ""

        binding.cvProfileDetail.setOnClickListener {

        }

        // Disable Send button if Detail view is showing my Info
        if (id == data.id) {
            binding.imgPromisProfile.visibility = View.INVISIBLE
            binding.imgPromisProfile.isEnabled = false
        }

        binding.imgScheduleProfile.setOnClickListener {
            val intent = Intent(this, ProfileMonthlyScheduleActivity::class.java)
            intent.putExtra("id", data.id)
            startActivity(intent)
        }

        binding.imgPromisProfile.setOnClickListener {
            val intent = Intent(this, ProfileDailyScheduleAddActivity::class.java)
            intent.putExtra("data", data)
            intent.putExtra("id", id)
            startActivity(intent)
        }

        binding.imgDmProfile.setOnClickListener {

        }

        binding.btnEdit.setOnClickListener {
            val intent = Intent(baseContext, ProfileEditActivity::class.java)
            intent.putExtra("status", data.status)
            intent.putExtra("food", data.food)
            intent.putExtra("hobby", data.hobby)
            intent.putExtra("favorites", data.favorites)
            intent.putExtra("weekend", data.weekend)
            startActivity(intent)
        }
    }
}