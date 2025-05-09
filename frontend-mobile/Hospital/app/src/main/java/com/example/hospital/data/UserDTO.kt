package com.example.hospital.data

data class UserDTO(
    val userId: Int = 0,
    val username: String,
    val password: String,
    val email: String,
    val role: String  // "PATIENT", "ADMIN", etc.

)