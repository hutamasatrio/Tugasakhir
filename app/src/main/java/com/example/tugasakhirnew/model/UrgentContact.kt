package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class UrgentContact(
    @SerializedName("data")
    val `data`: List<DataUrgentContact?>,
    @SerializedName("status")
    val status: Boolean
)