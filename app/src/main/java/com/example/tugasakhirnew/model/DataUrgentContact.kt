package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class DataUrgentContact(
    @SerializedName("email")
    val email: String,
    @SerializedName("noTelpon")
    val noTelpon: String,
    @SerializedName("urgentAddress")
    val urgentAddress: String,
    @SerializedName("urgentContactId")
    val urgentContactId: String,
    @SerializedName("urgentDescription")
    val urgentDescription: String,
    @SerializedName("urgentName")
    val urgentName: String,
    @SerializedName("urgentRelationship")
    val urgentRelationship: String,
    @SerializedName("userId")
    val userId: String
)