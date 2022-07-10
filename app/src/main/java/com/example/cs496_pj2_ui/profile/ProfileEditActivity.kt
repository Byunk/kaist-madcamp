package com.example.cs496_pj2_ui.profile

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs496_pj2_ui.databinding.ProfileEditActivityBinding

class ProfileEditActivity : AppCompatActivity() {

    private lateinit var binding: ProfileEditActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ProfileEditActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.etStatusEdit.setText(intent.getStringExtra("status") ?: "")
        binding.etFoodEdit.setText(intent.getStringExtra("food") ?: "")
        binding.etHobbyEdit.setText(intent.getStringExtra("hobby") ?: "")
        binding.etFavoritesEdit.setText(intent.getStringExtra("favorites") ?: "")
        binding.etWeekend.setText(intent.getStringExtra("weekend") ?: "")

        binding.btnSaveEdit.setOnClickListener {

        }

        binding.btnCancelEdit.setOnClickListener {
            finish()
        }
    }
}