package com.example.wanderbook.data.local.remote

import com.example.wanderbook.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST
import com.example.wanderbook.model.LoginResponse
import com.example.wanderbook.model.RegisterRequest
import com.example.wanderbook.model.RegisterResponse
import com.example.wanderbook.model.VerificationRequest
import com.example.wanderbook.model.VerificationResponse
import retrofit2.http.PUT

interface AuthApi {
    @POST("/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>

    @POST("/auth/register")
    suspend fun register(@Body request: RegisterRequest): Response<RegisterResponse>

    @PUT("/auth/verificate")
    suspend fun verify(@Body request: VerificationRequest): Response<VerificationResponse>
}