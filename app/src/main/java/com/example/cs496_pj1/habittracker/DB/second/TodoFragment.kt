package com.example.cs496_pj1.habittracker.DB.second

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.cs496_pj1.MainActivity

class TodoFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private var todoDb : TodoDB? = null
    private var todoList = listOf<Todo>()

    lateinit var mAdapter: TodoAdapter

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }



}