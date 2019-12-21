package com.example.basiccalendardisplay

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main4.*

class Main4Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)


        val home2=findViewById<ImageView>(R.id.home2)
//To navigate to home page
        home2.setOnClickListener(){
            val intent= Intent(this,MainActivity::class.java)
            startActivity(intent)
        }

//to add picture from the gallery
        add2.setOnClickListener {
            //check the  permission
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                //permission accessing libraries
                if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) ==
                    PackageManager.PERMISSION_DENIED
                )
                {
                    //if permission not granted
                    val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
                    //Allowing to gallery popup
                    requestPermissions(permissions, PERMISSION_CODE);
                } else {
                    //permission if granted
                    chooseImageFromGallery();
                }
            } else {
                //system gallery
                chooseImageFromGallery();
            }
        }
    }

    private fun chooseImageFromGallery() {
        //to pick a image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        //startactivity that picked the image
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image picking code
        private val IMAGE_PICK_CODE = 1000;
        //Permission access code
        private val PERMISSION_CODE = 1001;
    }

    //to handle the permissions
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        //getting results
        grantResults: IntArray
    ) {
        when (requestCode) {
            PERMISSION_CODE -> {
                if (grantResults.size > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    //permission from popup granted
                    chooseImageFromGallery()
                } else {
                    //permission from popup denied
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    //handle result of picked image
    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE) {
            imagedisplay.setImageURI(data?.data)
        }
    }
}
