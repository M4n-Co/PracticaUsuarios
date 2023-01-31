package com.example.usuarios.propiedadesRV

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.usuarios.databinding.ItemUsuarioBinding
import com.example.usuarios.model.Results
import com.squareup.picasso.Picasso

class userViewHolder(view: View):RecyclerView.ViewHolder(view) {

    private val binding = ItemUsuarioBinding.bind(view)

    fun bind(usuario : Results, onClickListener:(Results)-> Unit){
        val nombreCom = usuario.name.first +" "+ usuario.name.last
        Picasso.get().load(usuario.picture.large).into(binding.ivFoto)
        binding.tvNombre.text = nombreCom
        binding.tvTel.text = usuario.phone
        binding.tvCel.text = usuario.cell
        itemView.setOnClickListener {onClickListener(usuario)}
    }
}