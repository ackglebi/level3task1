package com.example.userprofile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_create_profile.*

const val GALLERY_REQUEST_CODE = 100

class CreateProfileActivity : AppCompatActivity() {

    private var profileImageUri: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_profile)
        initViews()
    }

    private fun initViews() {
        openGalleryButton.setOnClickListener { onGalleryClick() }
        confirmButton.setOnClickListener { onConfirmClick() }
    }

    private fun onGalleryClick() {
        // create gallery intent
        val galleryIntent = Intent(Intent.ACTION_PICK)

        // sets the type image/*, this ensures that only components of type image are selected
        galleryIntent.type = "image/*"

        // start tthe acivity using the gallery intent
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE)
    }


    private fun onConfirmClick() {

        val profile = Profile(
            etFirstName.text.toString(),
            etLastName.text.toString(),
            etDescription.text.toString(),
            profileImageUri
        )

        val profileActivityIntent = Intent(this, ProfileActivity::class.java)
        profileActivityIntent.putExtra(PROFILE_EXTRA, profile)
        startActivity(profileActivityIntent)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        // TODO weet niet of deze super cal hoort
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                GALLERY_REQUEST_CODE -> {
                    profileImageUri = data?.data
                    profilePicture.setImageURI(profileImageUri)
                }
            }
        }
    }
}