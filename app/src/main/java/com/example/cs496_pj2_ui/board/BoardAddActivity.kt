package com.example.cs496_pj2_ui.board

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.cs496_pj2_ui.databinding.BoardAddActivityBinding

class BoardAddActivity : AppCompatActivity() {

    private lateinit var binding: BoardAddActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BoardAddActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}