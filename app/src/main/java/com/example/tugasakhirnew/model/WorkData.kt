package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class WorkData(
    @SerializedName("description")
    val description: String,
    @SerializedName("idExperience")
    val idExperience: String,
    @SerializedName("posisition")
    val posisition: String,
    @SerializedName("timeline")
    val timeline: String,
    @SerializedName("userId")
    val userId: String,
    @SerializedName("workPlace")
    val workPlace: String
)