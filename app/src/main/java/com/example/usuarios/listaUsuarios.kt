package com.example.usuarios

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.usuarios.databinding.FragmentListaUsuariosBinding
import com.example.usuarios.model.Results
import com.example.usuarios.model.datosUsusarios
import com.example.usuarios.propiedadesRV.usuariosAdapter
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class listaUsuarios : Fragment() {
    companion object {
        const val KEY = "key"
        const val USUARIO = "Usuario"
    }
    lateinit var _binding : FragmentListaUsuariosBinding
    val binding : FragmentListaUsuariosBinding get() = _binding
    private lateinit var adaptador: usuariosAdapter
    private val usuariosL = mutableListOf<Results>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentListaUsuariosBinding.inflate(inflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iniRecycleV()
        generarUsuarios()
        binding.swipe.setOnRefreshListener {
            generarUsuarios()
            binding.swipe.isRefreshing = false
        }
    }

    private fun iniRecycleV(){
        adaptador = usuariosAdapter(usuariosL, {onItemSelected(it)})
        binding.rvListUsuarios.layoutManager = LinearLayoutManager(activity)
        binding.rvListUsuarios.adapter = adaptador
    }
     fun onItemSelected(usuario:Results){
        setFragmentResult(KEY, bundleOf(
            USUARIO to usuario))
         findNavController().navigate(R.id.listUsToUser)
     }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://randomuser.me/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun generarUsuarios(){
        viewLifecycleOwner.lifecycleScope.launch {
            val call : Response<datosUsusarios> = getRetrofit().create(APIService::class.java).getUsusarios()
            if(call.isSuccessful){
                val usuarios : List<Results> = call.body()!!.results
                val listUsuarios : List<Results> = usuarios
                usuariosL.clear()
                usuariosL.addAll(listUsuarios)
                adaptador.notifyDataSetChanged()
            }else{
                Toast.makeText(activity,R.string.aviso,Toast.LENGTH_LONG).show()
            }
        }
    }
}

