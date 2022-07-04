package com.example.cs496_pj1.habittracker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.CalendarDetailRowBinding
import com.example.cs496_pj1.models.Habit

class CalendarDetailAdapter(val habitList: ArrayList<Habit>) : RecyclerView.Adapter<CalendarDetailAdapter.CustomViewHolder>() {

    private lateinit var binding: CalendarDetailRowBinding
    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CalendarDetailAdapter.CustomViewHolder {
        context = parent.context
        binding = CalendarDetailRowBinding.inflate(LayoutInflater.from(context))

        val view = LayoutInflater.from(context).inflate(R.layout.calendar_detail_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    override fun onBindViewHolder(holder: CalendarDetailAdapter.CustomViewHolder, position: Int) {
        holder.bind(habitList[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val todoImage =  binding.todoImage
        val todoText = binding.todoText
        val todoSlider = binding.todoSlider

        fun bind(item: Habit) {
            //Temp
            todoImage.setImageResource(R.drawable.dodam)

            todoText.text = item.obj
            //TODO: todoSeekbar config according to whether the user finish todo or not


        }


    }

}