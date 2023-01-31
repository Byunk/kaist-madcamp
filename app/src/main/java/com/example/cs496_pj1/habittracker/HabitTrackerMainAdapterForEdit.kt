package com.example.cs496_pj1.habittracker

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.databinding.ActivityHabitTrackerEditBinding
import com.example.cs496_pj1.databinding.HabitRowBinding
import com.example.cs496_pj1.models.Habit
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class HabitTrackerMainAdapterForEdit(val habitArray: ArrayList<Habit>, val date: String) : RecyclerView.Adapter<HabitTrackerMainAdapterForEdit.CustomViewHolder>() {
    lateinit var context: Context
    private lateinit var binding: HabitRowBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HabitTrackerMainAdapterForEdit.CustomViewHolder {

        context = parent.context
        binding = HabitRowBinding.inflate(LayoutInflater.from(context), parent, false)
        return CustomViewHolder(binding.root)
    }

    override fun getItemCount(): Int {
        return habitArray.size
    }

    override fun onBindViewHolder(holder: HabitTrackerMainAdapterForEdit.CustomViewHolder, position: Int) {
        holder.bind(habitArray[position])
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val habit = binding.tvHabitItem
        val start = binding.startdateTextview
        val end = binding.enddateTextview

        fun bind(item: Habit) {
            habit.text = item.todo
            start.text = date2str(item.start)

            if (item.end != null) {
                end.text = date2str(item.end!!)
            } else {
                end.text = ""
            }

            itemView.setOnClickListener {
                val intent = Intent(context, HabitTrackerEditActivity::class.java).apply {
                    putExtra("todo", item.todo)
                    putExtra("start", item.start.time)
                    putExtra("end", item.end?.time)
                }.run { context.startActivity(this) }
            }
        }
    }

    private fun date2str(date: Date): String {
        val year = SimpleDateFormat("yyyy", Locale.KOREA).format(date).toInt()
        val month = SimpleDateFormat("MM", Locale.KOREA).format(date).toInt()-1
        val day = SimpleDateFormat("dd", Locale.KOREA).format(date).toInt()

        val result = Calendar.getInstance().run {
            set(year, month, day)
            time
        }

        return SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA).format(result).toString()
    }

    private fun dateString2Date(dateString: String): Date {
        val formatter = SimpleDateFormat("yyyy년 MM월 dd일")
        return formatter.parse(dateString)
    }
}