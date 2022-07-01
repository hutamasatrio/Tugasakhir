package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class Work(
    @SerializedName("data")
    val `data`: List<WorkData>,
    @SerializedName("status")
    val status: Boolean
)