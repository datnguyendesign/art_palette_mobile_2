package com.example.artpalette.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.artpalette.model.ImageModel
import com.example.artpalette.R
import com.makeramen.roundedimageview.RoundedImageView

class StaggeredAdapter(private val dataList: ArrayList<ImageModel>, private val context: Context): RecyclerView.Adapter<StaggeredAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val staggeredImages: RoundedImageView = itemView.findViewById(R.id.staggeredImages)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_staggered, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        Glide.with(context).load(dataList[position].imageUrl).into(holder.staggeredImages)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}