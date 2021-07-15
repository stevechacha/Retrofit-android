package com.steve.retrofit.data.repository

import com.steve.retrofit.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getUsers() = apiHelper.getUsers()
}