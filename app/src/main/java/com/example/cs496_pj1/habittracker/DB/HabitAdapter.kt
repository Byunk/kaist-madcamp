package com.example.cs496_pj1.habitList.db

import android.content.Context
import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R

class HabitAdapter(val context: Context): RecyclerView.Adapter<HabitAdapter.HabitViewHolder>() {
    private var list = mutableListOf<HabitDTO>()

    inner class HabitViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var title = itemView.findViewById<TextView>(R.id.tv_habit_item)
        var timestamp = itemView.findViewById<TextView>(R.id.tv_timeStamp)
        var checkbox = itemView.findViewById<CheckBox>(R.id.cbCheck)

        fun onBind(data: HabitDTO) {
            title.text = data.title
            timestamp.text = data.timestamp
            checkbox.isChecked = data.isChecked

            if (data.isChecked) {
                title.paintFlags = title.paintFlags or STRIKE_THRU_TEXT_FLAG
            } else {
                title.paintFlags = title.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
            }

            checkbox.setOnClickListener{
                itemCheckBoxClickListener.onClick(it, layoutPosition, list[layoutPosition].id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HabitViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.habit_item, parent, false)
        return HabitViewHolder(view)
    }

    override fun onBindViewHolder(holder: HabitViewHolder, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun update(newList: MutableList<HabitDTO>) {
        this.list = newList
        notifyDataSetChanged()
    }

    interface ItemCheckBoxClickListener {
        fun onClick(view: View, position: Int, itemId: Long)
    }

    private lateinit var itemCheckBoxClickListener: ItemCheckBoxClickListener

    fun setItemCheckBoxClickListener(
        itemCheckBoxClickListener: ItemCheckBoxClickListener
    ) {
        this.itemCheckBoxClickListener = itemCheckBoxClickListener
    }
}