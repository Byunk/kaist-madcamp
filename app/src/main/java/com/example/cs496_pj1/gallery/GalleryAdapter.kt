package com.example.cs496_pj1.gallery

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.cs496_pj1.R
import com.example.cs496_pj1.contacts.ContactsAdapter
import com.example.cs496_pj1.models.Images

class GalleryAdapter(val imageList: ArrayList<Images>) : RecyclerView.Adapter<GalleryAdapter.CustomViewHolder> ()
{

    private lateinit var context: Context

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.gallery_item, parent, false)
        context = parent.context
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val resourceID = context.resources.getIdentifier(
            imageList[position].name,
            "drawable",
            context.packageName
        )
        Glide.with(context).load(resourceID).into(holder.image)

        //holder.itemView.setOnClickListener()
    }

    override fun getItemCount(): Int {
        return imageList.size
    }

    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.iv_img)
    }

}