package com.example.artpalette.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.MimeTypeMap
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.artpalette.model.ImageModel
import com.example.artpalette.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.ismaeldivita.chipnavigation.ChipNavigationBar

class UploadActivity : AppCompatActivity() {

    private val databaseReference = FirebaseDatabase.getInstance().getReference("Images")
    private val storageReference = FirebaseStorage.getInstance().reference
    private var imageUri: Uri? = null
    private var uploadCaption: EditText? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload)

        val uploadButton = findViewById<FloatingActionButton>(R.id.uploadButton)
        uploadCaption = findViewById(R.id.uploadCaption)
        val uploadImage = findViewById<ImageView>(R.id.uploadImage)
        progressBar = findViewById<ProgressBar>(R.id.progressBar)
        progressBar!!.visibility = View.INVISIBLE

        val activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                imageUri = data?.data
                uploadImage.setImageURI(imageUri)
            } else {
                Toast.makeText(this@UploadActivity, "No Image Selected", Toast.LENGTH_SHORT).show()
            }
        }

        uploadImage.setOnClickListener {
            val photoPicker = Intent()
            photoPicker.setAction(Intent.ACTION_GET_CONTENT)
            photoPicker.setType("image/*")
            activityResultLauncher.launch(photoPicker)
        }

        uploadButton.setOnClickListener {
            if (imageUri != null) {
                uploadToFirebase(imageUri!!)
            } else {
                Toast.makeText(this@UploadActivity, "Please select image", Toast.LENGTH_SHORT).show()
            }
        }

        val menu = findViewById<ChipNavigationBar>(R.id.menu)
        menu.setItemSelected(R.id.add)
        menu.setOnItemSelectedListener {
            if (it == R.id.home) {
                startActivity(Intent(this@UploadActivity, PrimaryActivity::class.java))
            }
            if (it == R.id.add) {
                startActivity(Intent(this@UploadActivity, AddImageActivity::class.java))
            }
        }
    }

    private fun uploadToFirebase(uri: Uri) {
        val caption = uploadCaption?.text.toString()
        val imageReference: StorageReference = storageReference.child("${System.currentTimeMillis()}.${getFileExtension(uri)}")

        imageReference.putFile(uri).addOnSuccessListener {
            imageReference.downloadUrl.addOnSuccessListener {
                val imageModel = ImageModel(title = caption ,"me", uri.toString())
                val key = databaseReference.push().key
                if (key != null) {
                    databaseReference.child(key).setValue(imageModel)
                }
                progressBar?.visibility = View.INVISIBLE
                Toast.makeText(this@UploadActivity, "Uploaded", Toast.LENGTH_SHORT).show()
                val intent = Intent(this@UploadActivity, AddImageActivity::class.java)
                startActivity(intent)
                finish()
            }
        }.addOnProgressListener {
            progressBar?.visibility = View.VISIBLE
        }.addOnFailureListener {
            progressBar?.visibility = View.INVISIBLE
            Toast.makeText(this@UploadActivity, "Failed", Toast.LENGTH_SHORT).show()
        }


    }

    private fun getFileExtension(fileUri: Uri): String? {
        val contentResolver = contentResolver
        val mime = MimeTypeMap.getSingleton()
        return mime.getExtensionFromMimeType(contentResolver.getType(fileUri))
    }
}