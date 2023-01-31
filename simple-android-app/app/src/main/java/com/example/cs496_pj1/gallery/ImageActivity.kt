package com.example.cs496_pj1.gallery

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.cs496_pj1.R
import com.example.cs496_pj1.models.Images
import com.github.chrisbanes.photoview.PhotoView
//import kotlinx.android.synthetic.main.activity_image.*
import kotlinx.coroutines.NonDisposableHandle.parent

lateinit var imgList: ArrayList<Images>

class ImageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)

        val idx = intent.getIntExtra("img_idx", 111)

        val image_detail = findViewById<ViewPager2>(R.id.img_detail)
        image_detail.adapter = ViewPagerAdapter(getImgList())
        image_detail.setCurrentItem(idx, false)

        val btn_close = findViewById<ImageButton>(R.id.btn_close)
        btn_close.setOnClickListener(){
            finish()
        }

    }

    private fun getImgList(): ArrayList<Int> {
        return arrayListOf(
            R.drawable.img01,
            R.drawable.img02,
            R.drawable.img03,
            R.drawable.img04,
            R.drawable.img05,
            R.drawable.img06,
            R.drawable.img07,
            R.drawable.img08,
            R.drawable.img09,
            R.drawable.img10,
            R.drawable.img11,
            R.drawable.img12,
            R.drawable.img13,
            R.drawable.img14,
            R.drawable.img15,
            R.drawable.img16,
            R.drawable.img17,
            R.drawable.img18,
            R.drawable.img19,
            R.drawable.img20
        )
    }
}

class ViewPagerAdapter(photoList: ArrayList<Int>) : RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    var item = photoList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(parent)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        Glide.with(holder.image).load(item[position]).override(1000).into(holder.image)
    }

    override fun getItemCount(): Int {
        return item.size
    }


    inner class PagerViewHolder(parent: ViewGroup) :
        RecyclerView.ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.image_detail, parent, false)) {
            val image: PhotoView = itemView.findViewById<PhotoView>(R.id.pv_img_detail)
        }
    }