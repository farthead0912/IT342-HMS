package com.example.frontendmobile


import Staff.StaffHomeScreen
import Staff.StaffProfileScreen
import Staff.StaffScheduleScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.frontendmobile.ui.theme.FrontendmobileTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FrontendmobileTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login", // Set Login screen as the start screen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") { LoginScreen(navController) }
                        composable("signup") { SignupScreen(navController)}
                        composable("staffhomescreen") { StaffHomeScreen(navController)}
                        composable("staffScheduleScreen") { StaffScheduleScreen(navController)}
                        composable("staffprofilescreen") { StaffProfileScreen(navController) }


                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    FrontendmobileTheme {
        // Preview the MainActivity
    }
}
