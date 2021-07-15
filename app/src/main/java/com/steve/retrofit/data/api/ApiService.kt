package com.steve.retrofit.data.api

import com.steve.retrofit.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers(): List<User>
}