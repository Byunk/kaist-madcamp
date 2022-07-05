package com.example.cs496_pj1.habittracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.databinding.HabitRowBinding
import com.example.cs496_pj1.models.Habit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HabitTrackerMainAdapter(val habitList: ArrayList<Habit>) : RecyclerView.Adapter<HabitTrackerMainAdapter.CustomViewHolder>() {

    lateinit var context: Context
    private lateinit var binding: HabitRowBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitTrackerMainAdapter.CustomViewHolder {

        context = parent.context
        binding = HabitRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return habitList.size
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.bind(habitList[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habit = binding.tvHabitItem
        val start = binding.startdateTextview
        val end = binding.enddateTextview
        val slider = binding.todoSlider


        fun bind(item: Habit) {
            habit.text = item.todo
            start.text = date2str(item.start)

            if (item.end != null) {
                end.text = date2str(item.end!!)
            } else {
                end.text = ""
            }

            itemView.setOnClickListener {
                val intent = Intent(context, HabitTrackerDetailActivity::class.java).apply {
                    putExtra("start", item.start)

                    if (item.end != null) {
                        putExtra("end", item.end)
                    }
                }.run { context.startActivity(this) }
            }
        }

        private fun date2str(date: Date): String {
            return SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(date).toString()
        }

        private fun long2str(date: Long): String {
            val date = Date(date)
            return date2str(date)
        }
    }

}