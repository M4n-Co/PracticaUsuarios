package com.example.usuarios

import com.example.usuarios.model.datosUsusarios
import retrofit2.Response
import retrofit2.http.GET

interface APIService {
    @GET("?results=20")
    suspend fun getUsusarios(): Response<datosUsusarios>
}