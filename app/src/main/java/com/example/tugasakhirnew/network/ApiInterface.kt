package com.example.tugasakhirnew.network

import com.example.tugasakhirnew.model.UserProfile
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("/api/userprofile")
    suspend fun getData(): Response<UserProfile>
}