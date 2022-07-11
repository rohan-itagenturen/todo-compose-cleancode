package com.app.data.network

import com.app.data.network.entities.request.LoginRequest
import com.app.data.network.entities.response.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface UserAuthenticationApi {

    @POST("api/login")
    suspend fun login(@Body body: LoginRequest): LoginResponse
}