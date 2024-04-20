package com.example.artpalette.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.artpalette.R
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class PostActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setOnItemSelectedListener {
            if (it == R.id.home) {
                startActivity(Intent(this@PostActivity, PrimaryActivity::class.java))
                this@PostActivity.overridePendingTransition(
                    R.anim.animate_fate_enter,
                    R.anim.animate_fate_exit
                )
            }
            if (it == R.id.explore) {
                startActivity(Intent(this@PostActivity, ExploreActivity::class.java))
                this@PostActivity.overridePendingTransition(
                    R.anim.animate_fate_enter,
                    R.anim.animate_fate_exit
                )
            }
            if (it == R.id.add) {
                startActivity(Intent(this@PostActivity, AddImageActivity::class.java))
                this@PostActivity.  overridePendingTransition(
                    R.anim.animate_fate_enter,
                    R.anim.animate_fate_exit
                )
            }
            if (it == R.id.saved) {
                startActivity(Intent(this@PostActivity, SavedActivity::class.java))
                this@PostActivity.overridePendingTransition(
                    R.anim.animate_fate_enter,
                    R.anim.animate_fate_exit
                )
            }
            if (it == R.id.account) {
                startActivity(Intent(this@PostActivity, ProfileActivity::class.java))
                this@PostActivity.overridePendingTransition(
                    R.anim.animate_fate_enter,
                    R.anim.animate_fate_exit
                )
            }
        }
    }
}