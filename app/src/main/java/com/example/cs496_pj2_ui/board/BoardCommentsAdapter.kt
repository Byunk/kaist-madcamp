package com.example.cs496_pj2_ui.board

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.service.model.Comment

class BoardCommentsAdapter(val context: Context, val comments: ArrayList<Comment>): RecyclerView.Adapter<BoardCommentsAdapter.CustomViewHolder>() {
    override fun getItemCount(): Int {
        return comments.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BoardCommentsAdapter.CustomViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.board_comment_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: BoardCommentsAdapter.CustomViewHolder, position: Int) {
        // TODO: User name
        //holder.name.text = comments[position].
        holder.content.text = comments[position].content
    }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.tv_username_comment)
        val content = itemView.findViewById<TextView>(R.id.tv_comment_content)
        val btnDelete = itemView.findViewById<ImageButton>(R.id.btn_delete_comment)

        fun bind() {
            btnDelete.setOnClickListener {
                // TODO:  
            }
        }
    }
}