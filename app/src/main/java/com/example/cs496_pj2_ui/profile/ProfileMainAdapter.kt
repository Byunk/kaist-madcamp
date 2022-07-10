package com.example.cs496_pj2_ui.profile

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.cs496_pj2_ui.R
import com.example.cs496_pj2_ui.databinding.ProfileRowBinding
import com.example.cs496_pj2_ui.retrofitService.model.UserData

class ProfileMainAdapter(val context: Context): RecyclerView.Adapter<ProfileMainAdapter.CustomViewHolder>() {

    private lateinit var binding: ProfileRowBinding
    var friendsData: ArrayList<UserData> = arrayListOf()

    override fun getItemCount(): Int {
        return friendsData.size
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProfileMainAdapter.CustomViewHolder {
        binding = ProfileRowBinding.inflate(LayoutInflater.from(context))
        return CustomViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ProfileMainAdapter.CustomViewHolder, position: Int) {
        holder.name.text = friendsData[position].name
        holder.status.text = friendsData[position].status

        if (friendsData[position].imgUrl == null) {
            holder.imgProfile.setImageResource(R.drawable.account)
        } else {
            Glide.with(context).load(friendsData[position].imgUrl)
                .apply(RequestOptions().centerCrop())
                .into(holder.imgProfile)
        }
    }

    fun addFriendItem(friendData: UserData) {
        this.friendsData.add(friendData)
        notifyDataSetChanged()
    }

    inner class CustomViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val imgProfile = binding.imgProfile
        val name = binding.tvNameProfile
        val status = binding.tvStatusProfile
    }
}