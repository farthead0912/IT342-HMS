package com.example.hospital

import Admin.AdminHomeScreen
import Admin.AdminProfileScreen
import Admin.AdminRoomScreen
import Staff.StaffHomeScreen
import Staff.StaffAdmissionScreen
import Staff.StaffProfileScreen
import Staff.StaffMessageScreen
import Staff.StaffBillingScreen
import Staff.StaffRoomScreen
import Staff.StaffInventoryScreen
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
import com.example.hospital.ui.theme.HospitalTheme
import Patient.PatientHomeScreen
import Doctor.DoctorHomeScreen
import Doctor.DoctorProfileScreen
import Patient.PatientProfileScreen
import Staff.StaffAdmissionScreen
import Staff.viewmodel.AdmissionViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

object NavRoutes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val STAFF_HOME = "StaffHomeScreen"
    const val STAFF_ADMISSION = "StaffAdmissionScreen"
    const val STAFF_PROFILE = "StaffProfileScreen"
    const val STAFF_MESSAGE = "StaffMessageScreen"
    const val STAFF_BILLING = "StaffBillingScreen"
    const val STAFF_ROOM = "StaffRoomScreen"
    const val STAFF_INVENTORY = "StaffInventoryScreen"
    const val PATIENT_HOME = "PatientHomeScreen"
    const val DOCTOR_HOME = "DoctorHomeScreen"
    const val ADMIN = "AdminHomeScreen"
    const val ADMIN_ROOM= "AdminRoomScreen"
    const val ADMIN_PROFILE= "AdminProfileScreen"
    const val PATIENT_PROFILE = "PatientProfileScreen"
    const val DOCTOR_PROFILE = "DoctorProfileScreen"
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HospitalTheme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = NavRoutes.LOGIN,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(NavRoutes.LOGIN) { LoginScreen(navController) }
                        composable(NavRoutes.SIGNUP) { SignupScreen(navController) }
                        composable(NavRoutes.STAFF_HOME) { StaffHomeScreen(navController) }
                        composable(NavRoutes.ADMIN_PROFILE ){AdminProfileScreen(navController)}
                        composable(NavRoutes.PATIENT_PROFILE ){ PatientProfileScreen(navController) }
                        composable(NavRoutes.DOCTOR_PROFILE ){ DoctorProfileScreen(navController) }

                        composable(NavRoutes.ADMIN) { AdminHomeScreen(navController)}
                        composable(NavRoutes.ADMIN_ROOM) {
                            val userRole = "Admin" // or fetch dynamically from your app's logic
                            AdminRoomScreen(navController = navController, userRole = userRole)
                        }

                        composable(NavRoutes.STAFF_ADMISSION) {
                            val admissionViewModel: AdmissionViewModel = viewModel()
                            StaffAdmissionScreen(navController, admissionViewModel)
                        }
                        composable(NavRoutes.STAFF_PROFILE) { StaffProfileScreen(navController) }
                        composable(NavRoutes.STAFF_MESSAGE) { StaffMessageScreen(navController) }
                        composable(NavRoutes.STAFF_BILLING) { StaffBillingScreen(navController) }
                        composable(NavRoutes.STAFF_ROOM) { StaffRoomScreen(navController) }
                        composable(NavRoutes.STAFF_INVENTORY) { StaffInventoryScreen(navController) }
                        composable(NavRoutes.PATIENT_HOME) { PatientHomeScreen(navController) }
                        composable(NavRoutes.DOCTOR_HOME) { DoctorHomeScreen(navController) }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    HospitalTheme {
        val navController = rememberNavController()
        LoginScreen(navController) // You can change this to any screen you want to preview
    }
}
