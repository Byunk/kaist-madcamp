package com.example.cs496_pj2_ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.ProfileDetailActivityBinding
import com.example.cs496_pj2_ui.retrofitService.model.UserData

class ProfileDetailActivity : AppCompatActivity() {

    private lateinit var binding: ProfileDetailActivityBinding
    private lateinit var data: UserData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        data = intent.getParcelableExtra("data")!!

        binding.tvNameProfileDetail.text = data.name
        binding.tvStatusProfileDetail.text = data.status

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

        binding.imgCallProfile.setOnClickListener {

        }

        binding.imgDmProfile.setOnClickListener {

        }
    }
}