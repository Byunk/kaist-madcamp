package com.example.cs496_pj1.habittracker

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs496_pj1.MainActivity
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.ActivityHabitBinding

class TodoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var todoDb : TodoDB? = null
    private var todoList = listOf<Todo>()

    private var _binding: ActivityHabitBinding? = null
    private val binding get() = _binding!!
    lateinit var mAdapter: TodoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = ActivityHabitBinding.inflate(inflater, container, false)
        val root: View = binding.root

        //todoDb = TodoDB.getInstance(MainActivity)
        return root
    }


}