package com.example.cs496_pj1.habitList

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import android.widget.Button
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentGalleryBinding
import com.example.cs496_pj1.databinding.FragmentTap3Binding

class Tap3Fragment : Fragment() {

    private var _binding: FragmentTap3Binding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentTap3Binding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.btnHabit.setOnClickListener(View.OnClickListener() {
            val intent = Intent(context, HabitActivity::class.java)
            startActivity(intent)
        })
        return view
    }

    companion object {

    }
}
