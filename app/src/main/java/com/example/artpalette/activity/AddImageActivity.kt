package com.example.artpalette.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.artpalette.model.ImageModel
import com.example.artpalette.R
import com.example.artpalette.adapter.StaggeredAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class AddImageActivity : AppCompatActivity() {

    private var recyclerView: RecyclerView? = null
    private var dataList: ArrayList<ImageModel>? = null
    private var adapter: StaggeredAdapter? = null
    private var databaseReference: DatabaseReference = FirebaseDatabase.getInstance().getReference("Images")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_image)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this, UploadActivity::class.java)
            startActivity(intent)
            finish()
        }

        recyclerView = findViewById(R.id.recyclerview)

        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        dataList = ArrayList()
        adapter = StaggeredAdapter(dataList!!, this)
        recyclerView?.adapter = adapter

        databaseReference.addValueEventListener(object : ValueEventListener {
            @SuppressLint("NotifyDataSetChanged")
            override fun onDataChange(snapshot: DataSnapshot) {
                for (dataSnapshot in snapshot.children) {
                    val imageModel: ImageModel? = dataSnapshot.getValue(ImageModel::class.java)
                    imageModel?.let { dataList?.add(it) }

                }
                adapter?.notifyDataSetChanged()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setItemSelected(R.id.add)
        menu.setOnItemSelectedListener {
            if (it == R.id.home) {
                startActivity(Intent(this@AddImageActivity, PrimaryActivity::class.java))
                this@AddImageActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.explore) {
                startActivity(Intent(this@AddImageActivity, ExploreActivity::class.java))
                this@AddImageActivity.overridePendingTransition(
                    R.anim.animate_slide_in_left,
                    R.anim.animate_slide_out_right
                )
            }
            if (it == R.id.saved) {
                startActivity(Intent(this@AddImageActivity, SavedActivity::class.java))
                this@AddImageActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
            if (it == R.id.account) {
                startActivity(Intent(this@AddImageActivity, ProfileActivity::class.java))
                this@AddImageActivity.overridePendingTransition(
                    R.anim.animate_slide_left_enter,
                    R.anim.animate_slide_left_exit
                )
            }
        }

    }
}