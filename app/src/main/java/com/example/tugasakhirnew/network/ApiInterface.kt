package com.example.tugasakhirnew.network

import com.example.tugasakhirnew.model.DataProfile
import com.example.tugasakhirnew.model.DummyItem
import com.example.tugasakhirnew.model.UrgentContact
import com.example.tugasakhirnew.model.UserProfile
import com.google.gson.JsonObject
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {
    @GET("api/userprofile")
    fun getData(): Call<UserProfile>

    @GET("api/urgentcontact?userId={id}")
    fun getUserprofile(@Path("id") userId: String): Call<UserProfile>

    @GET("api/urgentcontact")
    fun getDataContact(): Call<UrgentContact>

    @FormUrlEncoded
    @POST("api/friendlist")
    fun saveToFirendList(
        @Field("friendid") friendId : String,
        @Field("userid") userId: String
    ) : Call<JsonObject>
}