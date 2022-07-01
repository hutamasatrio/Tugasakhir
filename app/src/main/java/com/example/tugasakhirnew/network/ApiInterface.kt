package com.example.tugasakhirnew.network

import com.example.tugasakhirnew.model.*
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("api/userprofile")
    fun getData(): Call<UserProfile>

    @GET("api/userprofile")
    fun getUserbyId(@Query("userId") userId: String): Call<UserProfile>

    @GET("api/urgentcontact")
    fun getDataContactbyId(@Query("userId")userId: String): Call<UrgentContact>

    @GET("api/Work")
    fun getWorkbyId(@Query("userId") userId: String): Call<Work>

    @GET("api/friendlist")
    fun getFriendListbyId(@Query("userId") userId: String): Call<FriendList>

    @FormUrlEncoded
    @POST("api/friendlist")
    fun saveToFirendList(
        @Field("friendid") friendId : String,
        @Field("userid") userId: String
    ) : Call<JsonObject>
}