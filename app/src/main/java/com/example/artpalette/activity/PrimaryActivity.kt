package com.example.artpalette.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.example.artpalette.adapter.ImageAdapter
import com.example.artpalette.model.ImageModel
import com.example.artpalette.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar
import kotlin.math.abs

class PrimaryActivity : AppCompatActivity() {

    private lateinit var imageAdapter: ImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_primary)
        val imageViewPager = findViewById<ViewPager2>(R.id.imageViewPager)
        val images: MutableList<ImageModel> = loadData()

        imageAdapter = ImageAdapter(images)

        imageViewPager.adapter = imageAdapter

        imageViewPager.clipToPadding = false
        imageViewPager.clipChildren = false
        imageViewPager.offscreenPageLimit = 3
        val recyclerView = imageViewPager.getChildAt(0) as RecyclerView
        recyclerView.overScrollMode = RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer { page, position ->
            val r = 1 - abs(position)
            page.scaleY = 0.95f + r * 0.05f
        }

        imageViewPager.setPageTransformer(compositePageTransformer)

        val postView = findViewById<ImageView>(R.id.postImgView).setOnClickListener {
            startActivity(Intent(this@PrimaryActivity, PostActivity::class.java))
            finish()
        }

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setItemSelected(R.id.home)
        menu.setOnItemSelectedListener {
            if (it == R.id.add) {
                startActivity(Intent(this@PrimaryActivity, AddImageActivity::class.java))
                this@PrimaryActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
            if (it == R.id.explore) {
                startActivity(Intent(this@PrimaryActivity, ExploreActivity::class.java))
                this@PrimaryActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
            if (it == R.id.saved) {
                startActivity(Intent(this@PrimaryActivity, SavedActivity::class.java))
                this@PrimaryActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
            if (it == R.id.account) {
                startActivity(Intent(this@PrimaryActivity, ProfileActivity::class.java))
                this@PrimaryActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
        }
    }



    private fun loadData(): MutableList<ImageModel> {
        val images: MutableList<ImageModel> = mutableListOf()
        images.add(ImageModel("France", "me", "https://www.travelandleisure.com/thmb/9xr8CFGR14sLvR4IhLwKV64fEV0=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/TAL-Eiffel-Tower-BESTFRANCE0323-dada0673f8764c099b68d01695ef8057.jpg", 4.8f))
        images.add(ImageModel("VietNam", "me", "https://vietnam.travel/sites/default/files/styles/top_banner/public/2022-08/VNAT%20Cover%203.png?itok=atu5sEjq", 4.8f))
        images.add(ImageModel("Pasta", "me", "https://www.foodandwine.com/thmb/fjNakOY7IcuvZac1hR3JcSo7vzI=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/FAW-recipes-pasta-sausage-basil-and-mustard-hero-06-cfd1c0a2989e474ea7e574a38182bbee.jpg", 4.8f))
        images.add(ImageModel("Wallpaper", "me", "https://wallpapers.com/images/featured/picture-en3dnh2zi84sgt3t.jpg", 4.8f))
        images.add(ImageModel("Nature", "me", "https://cdn.pixabay.com/photo/2016/11/08/04/49/jungle-1807476_640.jpg", 4.8f))
        return images
    }
}