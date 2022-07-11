package com.example.cs496_pj2_ui.promise

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.ProfileRowBinding
import com.example.cs496_pj2_ui.service.SocketService
import com.example.cs496_pj2_ui.service.model.PromiseRequest
import com.example.cs496_pj2_ui.service.model.PromiseRequestResponse
import com.example.cs496_pj2_ui.service.model.UserData

class PromiseAdapter(val context: Context): RecyclerView.Adapter<PromiseAdapter.CustomViewHolder>() {

    private lateinit var binding: ProfileRowBinding
    var responses: ArrayList<PromiseRequestResponse> = arrayListOf()

    override fun getItemCount(): Int {
        return responses.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        //binding = ProfileRowBinding.inflate(LayoutInflater.from(context))
        val view = LayoutInflater.from(context).inflate(R.layout.promise_row, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        // Profile
        if (responses[position].imgUrl == null) {
            holder.imgProfile.setImageResource(R.drawable.account)
        } else {
            Glide.with(context).load(responses[position].imgUrl)
                .apply(RequestOptions().centerCrop())
                .into(holder.imgProfile)
        }

        // Contents
        if (responses[position].message == null) {
            holder.message.text = responses[position].name + "님이 요청을 보내셨습니다."
        } else {
            holder.message.text = responses[position].name + " : " + responses[position].message
        }

        // Date Time
        val dateString = "${responses[position].date}일 ${responses[position].time}"
        holder.dateTime.text = dateString

        // Click Listeners
        /*
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
         */
    }

    fun updatePromises(response: ArrayList<PromiseRequestResponse>, isAccepted: Boolean = false) {
        this.responses = response
        notifyDataSetChanged()
    }

    fun clear() {
        this.responses.clear()
    }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgProfile = itemView.findViewById<ImageView>(R.id.img_profile_promise)!!
        val message = itemView.findViewById<TextView>(R.id.tv_promise_content)!!
        val dateTime = itemView.findViewById<TextView>(R.id.tv_date_time)!!
        val accept = itemView.findViewById<TextView>(R.id.tv_date_time)!!
        val reject = itemView.findViewById<TextView>(R.id.tv_date_time)!!
    }
}