package com.example.cs496_pj2_ui.board

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.BoardMainFragmentBinding
import com.example.cs496_pj2_ui.profile.ProfileMainAdapter

class BoardMainFragment : Fragment() {

    private lateinit var binding: BoardMainFragmentBinding

    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerAdapter: ProfileMainAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BoardMainFragmentBinding.inflate(inflater, container, false)

        //recyclerView = binding.rvBoardMain
        //recyclerAdapter = BoardMainAdapter(requireContext(), id)
        //recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        //recyclerView.adapter = recyclerAdapter
        return binding.root
    }
}