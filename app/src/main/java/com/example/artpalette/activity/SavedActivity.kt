package com.example.artpalette.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.artpalette.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class SavedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saved)

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setItemSelected(R.id.saved)
        menu.setOnItemSelectedListener {
            if (it == R.id.home) {
                startActivity(Intent(this@SavedActivity, PrimaryActivity::class.java))
                this@SavedActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.explore) {
                startActivity(Intent(this@SavedActivity, ExploreActivity::class.java))
                this@SavedActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.add) {
                startActivity(Intent(this@SavedActivity, AddImageActivity::class.java))
                this@SavedActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.account) {
                startActivity(Intent(this@SavedActivity, ProfileActivity::class.java))
                this@SavedActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
        }
    }
}