package com.example.cs496_pj2_ui

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cs496_pj2_ui.databinding.ProfileRowBinding
import com.example.cs496_pj2_ui.profile.ProfileDetailActivity
import com.example.cs496_pj2_ui.service.RetrofitService
import com.example.cs496_pj2_ui.service.SocketService
import com.example.cs496_pj2_ui.service.model.Promise
import com.example.cs496_pj2_ui.service.model.PromiseRequest
import com.example.cs496_pj2_ui.service.model.UserData
import org.w3c.dom.Text

class PromiseAdapter(val context: Context): RecyclerView.Adapter<PromiseAdapter.CustomViewHolder>() {

    private lateinit var binding: ProfileRowBinding
    var promises: ArrayList<Promise> = arrayListOf()

    override fun getItemCount(): Int {
        return promises.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PromiseAdapter.CustomViewHolder {
        //binding = ProfileRowBinding.inflate(LayoutInflater.from(context))
        val view = LayoutInflater.from(context).inflate(R.layout.profile_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: PromiseAdapter.CustomViewHolder, position: Int) {
        // Profile
        if (promises[position].senderImgUrl == null) {
            holder.imgProfile.setImageResource(R.drawable.account)
        } else {
            Glide.with(context).load(promises[position].senderImgUrl)
                .apply(RequestOptions().centerCrop())
                .into(holder.imgProfile)
        }

        // Contents
        if (promises[position].contents == null) {
            holder.content.text = promises[position].senderName + "님이 요청을 보내셨습니다."
        } else {
            holder.content.text = promises[position].senderName + " : " + promises[position].contents
        }

        // Date Time
        holder.dateTime.text = promises[position].dateString

        // Click Listeners
        val mSocket = SocketService.getSocket()
        holder.accept.setOnClickListener {
            mSocket.emit("response", true)
            promises.removeAt(position)
            notifyDataSetChanged()
        }

        holder.reject.setOnClickListener {
            mSocket.emit("response", false)
            promises.removeAt(position)
            notifyDataSetChanged()
        }
    }

    fun addPromise(promise: Promise) {
        this.promises.add(promise)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgProfile = itemView.findViewById<ImageView>(R.id.img_profile_promise)!!
        val content = itemView.findViewById<TextView>(R.id.tv_promise_content)!!
        val dateTime = itemView.findViewById<TextView>(R.id.tv_date_time)!!
        val accept = itemView.findViewById<TextView>(R.id.tv_date_time)!!
        val reject = itemView.findViewById<TextView>(R.id.tv_date_time)!!
    }
}