package com.example.hospital.data

import com.example.hospital.network.RetrofitClient
import retrofit2.Response

class AuthRepository {
    private val apiService = RetrofitClient.apiService

    suspend fun registerUser(user: UserDTO): Result<String> {
        return try {
            val response = when (user.role) {
                "ADMIN" -> apiService.registerAdmin(user)
                "DOCTOR" -> apiService.registerDoctor(user)
                "STAFF" -> apiService.registerStaff(user)
                else -> apiService.registerUser(user)
            }
            handleRegistrationResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun handleRegistrationResponse(response: Response<String>): Result<String> {
        return if (response.isSuccessful) {
            Result.success(response.body() ?: "Registration successful")
        } else {
            Result.failure(Exception(response.errorBody()?.string() ?: "Registration failed"))
        }
    }

    suspend fun login(loginData: LoginDTO): Result<JwtResponse> {
        return try {
            val response = apiService.login(loginData)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Login failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
