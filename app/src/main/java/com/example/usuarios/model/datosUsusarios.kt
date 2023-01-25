package com.example.usuarios.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class datosUsusarios(
    val results: List<Results>
):Parcelable