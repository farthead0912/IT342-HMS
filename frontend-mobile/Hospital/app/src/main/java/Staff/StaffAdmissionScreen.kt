package Staff


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import Staff.data.Admission
import Staff.repository.AdmissionRepository
import Staff.viewmodel.AdmissionViewModel


@Composable
fun StaffAdmissionScreen(navController: NavController, admissionViewModel: AdmissionViewModel) {
    val primaryColor = Color(0xFF1976D2)
    val lightGray = Color(0xFFF9F9F9)
    var selectedTab by remember { mutableStateOf(2) }

    var showCreateDialog by remember { mutableStateOf(false) }
    var selectedAdmission by remember { mutableStateOf<Admission?>(null) }

    Scaffold(
        containerColor = lightGray,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    when (it) {
                        0 -> navController.navigate("StaffHomeScreen") {
                            popUpTo("StaffScheduleScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        1 -> navController.navigate("StaffMessageScreen") {
                            popUpTo("StaffScheduleScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        2 -> {} // Already on Schedule screen, no need to navigate
                        3 -> navController.navigate("StaffProfileScreen") {
                            popUpTo("StaffScheduleScreen") { inclusive = true }
                            launchSingleTop = true }
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            // Screen Content (Appointments, Work Shifts, etc.)
            Text("Your Schedule", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // Sample Appointments and Shifts...

            // Add Create Admission Button
            Button(onClick = { showCreateDialog = true }) {
                Text("Create Admission")
            }

            // Display existing admissions if any (editable)
            selectedAdmission?.let { admission ->
                AdmissionCard(admission, onEditClick = {
                    selectedAdmission = admission
                    showCreateDialog = true
                })
            }
        }
    }

    // Show Create/Edit Admission Dialog
    if (showCreateDialog) {
        AdmissionFormDialog(
            admission = selectedAdmission,
            onDismiss = { showCreateDialog = false },
            onSubmit = { admission ->
                if (admission != null) {
                    if (admission.admissionId == 0) {
                        admissionViewModel.createAdmission(admission)
                    } else {
                        admissionViewModel.updateAdmission(admission.admissionId, admission)
                    }
                }
                showCreateDialog = false
            }
        )
    }
}

@Composable
fun AdmissionFormDialog(admission: Admission?, onDismiss: () -> Unit, onSubmit: (Admission?) -> Unit) {
    var doctorId by remember { mutableStateOf(admission?.doctorId ?: 0) }
    var patientId by remember { mutableStateOf(admission?.patientId ?: 0) }
    var roomId by remember { mutableStateOf(admission?.roomId ?: 0) }
    var admissionDate by remember { mutableStateOf(admission?.admissionDate ?: "") }
    var dischargeDate by remember { mutableStateOf(admission?.dischargeDate ?: "") }
    var admissionReason by remember { mutableStateOf(admission?.admissionReason ?: "") }
    var status by remember { mutableStateOf(admission?.status ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text("Admission Form") },
        text = {
            Column {
                TextField(value = admissionDate, onValueChange = { admissionDate = it }, label = { Text("Admission Date") })
                TextField(value = dischargeDate, onValueChange = { dischargeDate = it }, label = { Text("Discharge Date") })
                TextField(value = admissionReason, onValueChange = { admissionReason = it }, label = { Text("Reason") })
                TextField(value = status, onValueChange = { status = it }, label = { Text("Status") })
                // Add fields for doctorId, patientId, and roomId based on your requirements
            }
        },
        confirmButton = {
            Button(onClick = {
                val updatedAdmission = Admission(
                    admissionId = admission?.admissionId ?: 0,
                    doctorId = doctorId,
                    patientId = patientId,
                    roomId = roomId,
                    admissionDate = admissionDate,
                    dischargeDate = dischargeDate,
                    admissionReason = admissionReason,
                    status = status
                )
                onSubmit(updatedAdmission)
            }) {
                Text("Submit")
            }
        },
        dismissButton = {
            Button(onClick = onDismiss) {
                Text("Cancel")
            }
        }
    )
}

@Composable
fun AdmissionCard(admission: Admission, onEditClick: () -> Unit) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Admission ID: ${admission.admissionId}")
            Text("Doctor ID: ${admission.doctorId}")
            Text("Patient ID: ${admission.patientId}")
            Text("Room ID: ${admission.roomId}")
            Text("Date: ${admission.admissionDate}")
            Text("Reason: ${admission.admissionReason}")
            Text("Status: ${admission.status}")

            Spacer(modifier = Modifier.height(8.dp))
            Button(onClick = onEditClick) {
                Text("Edit")
            }
        }
    }
}


