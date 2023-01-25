package com.example.usuarios.propiedadesRV

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.usuarios.R
import com.example.usuarios.model.Results

class usuariosAdapter(val usuarios: List<Results>, private var onClickListener:(Results)-> Unit):RecyclerView.Adapter<userViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): userViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return userViewHolder(layoutInflater.inflate(R.layout.item_usuario,parent,false))
    }

    override fun onBindViewHolder(holder: userViewHolder, position: Int) {
        val item : Results = usuarios[position]
        holder.bind(item, onClickListener)
    }

    override fun getItemCount(): Int = usuarios.size

}