package com.example.hms


import Staff.StaffBillingScreen
import Staff.StaffHomeScreen
import Staff.StaffInventoryScreen
import Staff.StaffMessageScreen
import Staff.StaffProfileScreen
import Staff.StaffRoomScreen
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.hms.ui.theme.HMSTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HMSTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login", // Set Login screen as the start screen
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") { LoginScreen(navController) }
                        composable("signup") { SignupScreen(navController)}
                        composable("StaffHomeScreen") { StaffHomeScreen(navController)}
                        composable("StaffScheduleScreen") { StaffScheduleScreen(navController)}
                        composable("StaffProfileScreen") { StaffProfileScreen(navController) }
                        composable("StaffMessageScreen") { StaffMessageScreen(navController)}
                        composable("StaffBillingScreen") { StaffBillingScreen(navController) }
                        composable("StaffRoomScreen") { StaffRoomScreen(navController)}
                        composable("StaffInventoryScreen") { StaffInventoryScreen(navController)}

                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HMSTheme {
        // Preview the MainActivity
    }
}
