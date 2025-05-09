package com.example.hospital.network

import com.example.hospital.data.JwtResponse
import com.example.hospital.data.LoginDTO
import com.example.hospital.data.UserDTO
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {
    // Registration endpoints
    @POST("api/auth/register")
    suspend fun registerUser(@Body user: UserDTO): Response<String>

    @POST("api/auth/admin/register")
    suspend fun registerAdmin(@Body user: UserDTO): Response<String>

    @POST("api/auth/doctor/register")
    suspend fun registerDoctor(@Body user: UserDTO): Response<String>

    @POST("api/auth/staff/register")
    suspend fun registerStaff(@Body user: UserDTO): Response<String>

    // Login
    @POST("api/auth/login")
    suspend fun login(@Body loginData: LoginDTO): Response<JwtResponse>
}