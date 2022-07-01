package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class FriendList(
    @SerializedName("data")
    val data: List<FriendListData>,
    @SerializedName("status")
    val status: Boolean
)