package com.example.usuarios.model
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Location(
    val city: String,
    val country: String,
):Parcelable