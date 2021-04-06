package com.example.recyclerviewkotlin

import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.recyclerviewkotlin.databinding.ActivityImagenDetalleBinding

class ImagenDetalle : AppCompatActivity() {
    private lateinit var binding:ActivityImagenDetalleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       binding = ActivityImagenDetalleBinding.inflate(layoutInflater)
       setContentView(binding.root)

        if(intent.extras != null){
            val url = intent.getStringExtra("imgurl")
         Glide.with(this).load(url).into(binding.photoView)
        }
    }
}