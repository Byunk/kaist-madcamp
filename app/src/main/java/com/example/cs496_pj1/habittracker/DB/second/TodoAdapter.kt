package com.example.cs496_pj1.habittracker.DB.second

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj1.R

class TodoAdapter(val context: Context, val todoList: List<Todo>):  RecyclerView.Adapter<TodoAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_todo_list, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(todoList[position])
    }

    override fun getItemCount(): Int {
        return todoList.size
    }

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!){
        var title = itemView?.findViewById<TextView>(R.id.tvTodoItem)
        var timeStamp = itemView?.findViewById<TextView>(R.id.tvTimeStamp)
        var checkbox = itemView?.findViewById<CheckBox>(R.id.cbCheck)

        fun bind(data: Todo){
            title?.text = data.title
            timeStamp?.text = data.timestamp
            checkbox?.isChecked = data.isChecked


        }
    }
}