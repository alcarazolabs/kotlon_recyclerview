package com.example.recyclerviewkotlin.Base

import android.view.View
import androidx.recyclerview.widget.RecyclerView
/* Esta clase abstracta sera una extensión que vamos a usar
*  cuando creemos el adaptador del reycyclerview.
* Cuando extengamos del ViewHolder del adaptador vamos a pasarle
* en vez de ViewHolder le pasaremos BaseViewHolder y asi sera mas
* facil crear el recyclerview.
*/
abstract class BaseViewHolder<T>(itemView: View) : RecyclerView.ViewHolder(itemView){
    abstract fun  bind(item: T,position:Int)
}