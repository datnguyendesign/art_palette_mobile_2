package com.example.artpalette.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.artpalette.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class ProfileActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setItemSelected(R.id.account)
        menu.setOnItemSelectedListener {
            if (it == R.id.home) {
                startActivity(Intent(this@ProfileActivity, PrimaryActivity::class.java))
                this@ProfileActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.explore) {
                startActivity(Intent(this@ProfileActivity, ExploreActivity::class.java))
                this@ProfileActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.add) {
                startActivity(Intent(this@ProfileActivity, AddImageActivity::class.java))
                this@ProfileActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.saved) {
                startActivity(Intent(this@ProfileActivity, SavedActivity::class.java))
                this@ProfileActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
        }
    }
}