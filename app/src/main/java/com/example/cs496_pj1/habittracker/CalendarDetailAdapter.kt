package com.example.cs496_pj1.habittracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R
import com.example.cs496_pj1.contacts.ContactsAdapter
import com.example.cs496_pj1.databinding.CalendarDetailRowBinding

class CalendarDetailAdapter : RecyclerView.Adapter<CalendarDetailAdapter.CustomViewHolder>() {

    private lateinit var binding: CalendarDetailRowBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarDetailAdapter.CustomViewHolder {
        context = parent.context
        binding = CalendarDetailRowBinding.inflate(LayoutInflater.from(context))

        val view = LayoutInflater.from(context).inflate(R.layout.contacts_row, parent, false)
        return CustomViewHolder(view)
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoImage =  itemView.findViewById<ImageView>(R.id.todoImage)
        val todoText = itemView.findViewById<TextView>(R.id.todoText)
        val todoSeekBar = itemView.findViewById<>()


    }

}