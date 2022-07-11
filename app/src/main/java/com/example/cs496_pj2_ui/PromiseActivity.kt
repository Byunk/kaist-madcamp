package com.example.cs496_pj2_ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj2_ui.databinding.PromiseActivityBinding
import com.example.cs496_pj2_ui.service.SocketService

class PromiseActivity : AppCompatActivity() {

    private lateinit var binding: PromiseActivityBinding
    private lateinit var adapter: PromiseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PromiseActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = PromiseAdapter(this)
        binding.rvPromise.adapter = adapter
        binding.rvPromise.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }

    override fun onResume() {
        super.onResume()

        val mSocket = SocketService.getSocket()
    }
}