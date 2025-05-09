package com.example.hospital

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.auth0.jwt.JWT

import com.example.hospital.data.LoginDTO
import com.example.hospital.viewmodel.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, viewModel: AuthViewModel = viewModel()) {
    val primaryColor = Color(0xFF1976D2)
    val lightGray = Color(0xFFF9F9F9)

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val authState by viewModel.authState.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(lightGray),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
        ) {
            Text(
                text = "MEDISYNC",
                color = primaryColor,
                style = MaterialTheme.typography.headlineMedium,
                fontSize = 28.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            Card(
                shape = RoundedCornerShape(24.dp),
                elevation = CardDefaults.cardElevation(8.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier
                        .padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    OutlinedTextField(
                        value = username,
                        onValueChange = { username = it },
                        label = { Text("Username") },
                        leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        label = { Text("Password") },
                        visualTransformation = PasswordVisualTransformation(),
                        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                        singleLine = true,
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.fillMaxWidth(),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = primaryColor,
                            unfocusedBorderColor = Color.Transparent,
                            focusedContainerColor = Color.White,
                            unfocusedContainerColor = Color.White
                        )
                    )

                    Button(
                        onClick = {
                            viewModel.login(LoginDTO(username = username, password = password))
                        },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = primaryColor)
                    ) {
                        Text("Login", fontSize = 16.sp)
                    }

                    TextButton(
                        onClick = { navController.navigate("signup") },
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    ) {
                        Text("Don't have an account? Sign Up", color = primaryColor)
                    }

                    // Show loading, success, or error state
                    when (authState) {
                        is AuthViewModel.AuthState.Loading -> {
                            CircularProgressIndicator(
                                color = primaryColor,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        is AuthViewModel.AuthState.Success -> {
                            val token = (authState as AuthViewModel.AuthState.Success).token
                            val userRole = extractRoleFromToken(token) // Function to extract role from token

                            LaunchedEffect(Unit) {
                                // Navigate based on role
                                when (userRole) {
                                    "STAFF" -> navController.navigate("StaffHomeScreen")
                                    "DOCTOR" -> navController.navigate("DoctorHomeScreen")
                                    "PATIENT" -> navController.navigate("PatientHomeScreen")
                                    "ADMIN" -> navController.navigate("AdminHomeScreen")
                                    else -> navController.navigate("defaultHomeScreen")
                                }
                            }
                        }
                        is AuthViewModel.AuthState.Error -> {
                            Text(
                                text = (authState as AuthViewModel.AuthState.Error).message,
                                color = Color.Red,
                                fontSize = 14.sp,
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            )
                        }
                        else -> {}
                    }
                }
            }
        }
    }
}

fun extractRoleFromToken(token: String): String {
    // Use a JWT library to decode the token and extract the user's role
    // This is just a placeholder function.
    val decodedToken = JWT.decode(token) // Assuming you're using a JWT library to decode
    return decodedToken.getClaim("role").asString() ?: "UNKNOWN"
}
