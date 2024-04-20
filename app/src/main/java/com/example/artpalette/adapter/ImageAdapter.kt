package com.example.artpalette.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.artpalette.model.ImageModel
import com.example.artpalette.R
import com.flaviofaria.kenburnsview.KenBurnsView
import com.squareup.picasso.Picasso

class ImageAdapter(private val images: MutableList<ImageModel>) : RecyclerView.Adapter<ImageAdapter.ImageViewHolder>() {

    class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val kbvImage = itemView.findViewById<KenBurnsView>(R.id.kbvImage)
        val textTitle = itemView.findViewById<TextView>(R.id.textTitle)
        val textCreator = itemView.findViewById<TextView>(R.id.textCreator)
        val textStarRating = itemView.findViewById<TextView>(R.id.textStarRating)

        fun setImageData(imageModel: ImageModel) {
            Picasso.get().load(imageModel.imageUrl).into(kbvImage)
            textTitle.text = imageModel.title
            textCreator.text = imageModel.creator
            textStarRating.text = imageModel.starRating.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        return ImageViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.item_image_homepage, parent, false
        ))
    }

    override fun getItemCount(): Int {
        return images.size
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.setImageData(images[position])
    }
}