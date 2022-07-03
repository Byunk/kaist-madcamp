package com.example.cs496_pj1.gallery

import android.media.Image
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cs496_pj1.R
import com.example.cs496_pj1.databinding.FragmentGalleryBinding
import com.example.cs496_pj1.models.Images
import org.json.JSONObject

class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!

//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // get name of image from json
        val assets = resources.assets
        val inputStream = assets.open("images.json")
        val jsonString = inputStream.bufferedReader().use{ it.readText() }

        // parsing json
        val imageList: ArrayList<Images> = arrayListOf()
        val jObject = JSONObject(jsonString)
        val jArray = jObject.getJSONArray("images")
        Log.d("BasicSyntax", "${jArray.length()}")
        for(i in 0 until jArray.length()) {
            val obj = jArray.getJSONObject(i)
            val name = obj.getString("name")
            imageList.add(Images(name))
        }
        // Layout
        binding.rvImages.layoutManager = GridLayoutManager(this.context, 3)
        binding.rvImages.setHasFixedSize(true)

        binding.rvImages.adapter = GalleryAdapter(imageList)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}