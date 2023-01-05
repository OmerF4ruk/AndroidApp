package com.example.app

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Deneme :AppCompatActivity(){

    private  var our_request_code:Int= 123
    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
    }
    fun takePhoto(view: View){
        val intent= Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if(intent.resolveActivity(packageManager)!=null){
            startActivityForResult(intent,our_request_code)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==our_request_code && resultCode== RESULT_OK){
            val imageView: ImageView = findViewById(R.id.image)

            val bitmap =data?.extras?.get("data")as Bitmap
            imageView.setImageBitmap(bitmap)
        }
    }

}