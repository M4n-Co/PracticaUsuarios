package com.example.usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.usuarios.databinding.FragmentDestallesUsuarioBinding
import com.example.usuarios.model.Results
import com.squareup.picasso.Picasso

class destallesUsuario : Fragment() {

    lateinit var _binding : FragmentDestallesUsuarioBinding
    val binding: FragmentDestallesUsuarioBinding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentDestallesUsuarioBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener {
            findNavController().navigate(R.id.detalles_lista)
            this.onDestroy()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener(listaUsuarios.KEY){requestKey:String, bundle :Bundle ->
            val usuario = bundle.getParcelable<Results>(listaUsuarios.USUARIO)
            val nombreCompleto = usuario!!.name.title+" "+
                    usuario.name.first+" "+
                    usuario.name.last
            val edad = getString(R.string.edad)+" "+usuario.dob.age.toString()
            val tel = getString(R.string.Tel)+" "+usuario.phone
            val cel = getString(R.string.Cel)+" "+usuario.cell
            val email = getString(R.string.email)+" "+usuario.email
            val pais = getString(R.string.pais)+" "+usuario.location.country
            val ciudad = getString(R.string.ciudad)+" "+usuario.location.city

            binding.tvNombreCompleto.text = nombreCompleto
            Picasso.get().load(usuario.picture.medium).into(binding.ivFoto)
            binding.tvEdad.text = edad
            binding.tvTelef.text = tel
            binding.tvCelu.text = cel
            binding.tvEmail.text = email
            binding.tvPais.text = pais
            binding.tvCiudad.text = ciudad
        }
    }
}