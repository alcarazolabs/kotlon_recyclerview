package com.example.recyclerviewkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.recyclerviewkotlin.Adaptadores.RecyclerAdapter
import com.example.recyclerviewkotlin.Models.Persona
import com.example.recyclerviewkotlin.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), RecyclerAdapter.onPersonaClickListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
    }

    private fun setupRecyclerView(){
        //Ctrl+P para ver que parametros recibe el objeto
        var listaPersonas:List<Persona> = listOf(
            Persona("Freddy Alc","https://static4.depositphotos.com/1005893/311/i/600/depositphotos_3110740-stock-photo-good-looking-asian.jpg","Me gusta la programación Android"),
            Persona("Chune Sha","https://image.freepik.com/free-photo/young-asian-girl-portrait-isolated_53876-70968.jpg","I'm from Hong Kong. Connected!"),
            Persona("Xi jian","https://i.pinimg.com/474x/03/57/f3/0357f311de8ab54c080d97d27a6c947e.jpg","I'm from China. Alibaba worker"))
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        //Agregar linea separadora al final de cada item
        binding.recyclerView.addItemDecoration(DividerItemDecoration(this,DividerItemDecoration.VERTICAL))
        binding.recyclerView.adapter = RecyclerAdapter(this,listaPersonas, this)

    }
    //Desde la vista, ya podemos hacer el click. El adaptador solo es para inflar la vista, no es una buena practica hacerlo en el adaptador
    override fun onImageClick(imagen: String) {
        val intent = Intent(this,ImagenDetalle::class.java)
        intent.putExtra("imgurl", imagen)
        startActivity(intent)
    }

    override fun onItemClick(nombre: String) {
        Toast.makeText(this,nombre,Toast.LENGTH_LONG).show()
    }

}