package com.example.hospital.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hospital.data.AuthRepository
import com.example.hospital.data.LoginDTO
import com.example.hospital.data.UserDTO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel(private val repository: AuthRepository = AuthRepository()) : ViewModel() {

    sealed class AuthState {
        object Idle : AuthState()
        object Loading : AuthState()
        data class Success(val message: String, val role: String, val token: String) : AuthState() // Add token here
        data class Error(val message: String) : AuthState()
    }

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun registerUser(user: UserDTO) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.registerUser(user)
            _authState.value = when {
                result.isSuccess -> AuthState.Success(result.getOrNull() ?: "Registered", user.role, "YourToken") // Add token here as placeholder
                else -> AuthState.Error(result.exceptionOrNull()?.message ?: "Unknown error")
            }
        }
    }

    fun login(loginData: LoginDTO) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            val result = repository.login(loginData)
            _authState.value = if (result.isSuccess) {
                val response = result.getOrNull()
                val role = response?.role ?: "Unknown"
                val token = response?.token ?: "YourToken" // Add token here
                AuthState.Success("Login successful", role, token)
            } else {
                AuthState.Error(result.exceptionOrNull()?.message ?: "Login failed")
            }
        }
    }
}

