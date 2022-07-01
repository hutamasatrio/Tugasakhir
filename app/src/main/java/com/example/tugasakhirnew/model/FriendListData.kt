package com.example.tugasakhirnew.model


import com.google.gson.annotations.SerializedName

data class FriendListData(
    @SerializedName("friendId")
    val friendId: String,
    @SerializedName("userId")
    val userId: String
)