package com.example.cs496_pj2_ui.board

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cs496_pj2_ui.databinding.BoardDetailActivityBinding
import com.example.cs496_pj2_ui.service.RetrofitService
import com.example.cs496_pj2_ui.service.model.Board


class BoardDetailActivity : AppCompatActivity() {

    private lateinit var binding: BoardDetailActivityBinding
    private lateinit var id: String
    private lateinit var board: Board

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = BoardDetailActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        id = intent.getStringExtra("id")!!
        board = intent.getParcelableExtra("board")!!

        binding.tvTitleBoardDetail.text = board.title
        binding.tvUserBoardDetail.text = board.username
        binding.tvVotesBoardDetail.text = board.votes?.let {
            it.toString() + " votes"
        } ?: "0 vote"
        binding.tvContentBoardDetail.text = board.content ?: ""

        val adapter = BoardCommentsAdapter(this, board.comments ?: arrayListOf())
        binding.rvCommentBoardDetail.adapter = adapter
        binding.rvCommentBoardDetail.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}