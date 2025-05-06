package com.example.wanderbook.data.local.remote

import com.example.wanderbook.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.wanderbook.model.LoginResponse

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}