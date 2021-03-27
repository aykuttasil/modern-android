package com.aykuttasil.data.remote

import com.aykuttasil.data.entities.UserData
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("users/{login}")
    suspend fun getUser(@Path("login") login: String): UserData
}
