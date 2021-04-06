package com.example.recyclerviewkotlin.Adaptadores

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerviewkotlin.Base.BaseViewHolder
import com.example.recyclerviewkotlin.Models.Persona
import com.example.recyclerviewkotlin.R
import com.example.recyclerviewkotlin.databinding.PersonasRowBinding

class RecyclerAdapter (private val context: Context,
                       private val listaPersonas:List<Persona>,
                        private val itemClickListener:onPersonaClickListener) : RecyclerView.Adapter<BaseViewHolder<*>>() {
    /* La listaPersonas es inmutable
    * El asterísco: BaseViewHolder<*>: Significa que este adapter puede tomar cualquier vista que nosotros
    * luego le vamos a declarar cuando creemos el ViewHolder dentro de este adapter
    * La ventaja de esto es que podemos crear varios ViewHolder dentro de este adaptador
    * y de una forma mucho mas simple de crear ViewTypes 'los viewTypes' cargar varias vistas en un solo adaptador
    * Ctrl + i para implementar los métodos.
    */
    //metodos del ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
       //Aqui es donde inflamos la vista que va a contener los datos
        //aqui nos pide que retornemos un ViewHolder
        return PersonasViewHolder(LayoutInflater.from(context).inflate(R.layout.personas_row, parent, false))
        //de esta manera inflamos el layout qur vamos a usara para bindear cada uno de los elementos
    }

    override fun getItemCount(): Int {
        //Este devuelve la lista de datos, la cantidad que tiene que inflar
        println("Tamaño de la lista: ${listaPersonas.size}")
        return listaPersonas.size
    }
    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        //Este metodo coge toda la informacion de lista y se la
        //agrega a cada uno de los elementos con el formato de la vista que acabamos de inflar en el onCreateViewHolder()
       //aqui le decimos que ponga la informacion en las celdas a partir de la lista
        when(holder){
            is PersonasViewHolder -> holder.bind(listaPersonas[position],position)
            else -> IllegalAccessException("No se paso el viewHolder en el bind")
        }
    }
    //Crear el viewHolder:
    //Se crea una INNERCLASS para evitar crear una clase o generar una instancia en el OnBIND, esta instancia puede seguir viva antes del garbage collector la elimine. El padre es el recyclerAdapter y es el que muere primero, luego el objeto. Por eso se crea una innerClass
    //entonces se crea una clase hija con innerClass para que cuando muera la clase principal RecyclerAdapter la hija tambien muera.
    // De esta manera se asegura una sola instancia del adapter y no varias instancias vivas cuando el adaptaer no este vivo

    inner class PersonasViewHolder(itemView: View) : BaseViewHolder<Persona>(itemView) {
        val binding = PersonasRowBinding.bind(itemView)
        override fun bind(item: Persona, position: Int) {
            //Este viewHolder nos va a obtener cada Persona que nosotros bindiemos al recyclerview
           //el itemView: es toda la vista todo el item completo
            binding.txtNombrePersona.setOnClickListener{ itemClickListener.onItemClick(item.nombre)}
            binding.imgPersona.setOnClickListener { itemClickListener.onImageClick(item.imagen) }

            binding.txtNombrePersona.text = item.nombre
            binding.txtDetallePersona.text = item.detalle
            Glide.with(context).load(item.imagen).into(binding.imgPersona)
        }
    }
    //esta interface se implementara sus metodos en el mainactivity
    interface onPersonaClickListener{
        fun onImageClick(imagen: String)
        fun onItemClick(nombre: String)
    }

}
