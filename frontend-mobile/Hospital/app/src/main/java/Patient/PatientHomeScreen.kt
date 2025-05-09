package Patient

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun PatientHomeScreen(navController: NavController) {
    val primaryColor = Color(0xFF1976D2)
    val lightGray = Color(0xFFF9F9F9)

    var selectedTab by remember { mutableStateOf(0) }

    Scaffold(
        containerColor = lightGray,
        bottomBar = {
            BottomNavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            when (selectedTab) {
                0 -> PatientHomeContent()
                1 -> navController.navigate("PatientMessageScreen") {
                    popUpTo("PatientHomeScreen") { inclusive = true }
                    launchSingleTop = true
                }
                2 -> navController.navigate("PatientAppointmentScreen") {
                    popUpTo("PatientHomeScreen") { inclusive = true }
                    launchSingleTop = true
                }
                3 -> navController.navigate("PatientProfileScreen") {
                    popUpTo("PatientHomeScreen") { inclusive = true }
                    launchSingleTop = true
                }
            }
        }
    }
}

@Composable
fun PatientHomeContent() {
    val primaryColor = Color(0xFF1976D2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Welcome Back, Alex", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Your next appointment: May 10, 2025", fontSize = 14.sp, color = Color.Gray)
            }
            Icon(Icons.Default.Notifications, contentDescription = null, tint = primaryColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Your Records", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        MedicalRecordCard()

        Spacer(modifier = Modifier.height(16.dp))

        Text("Upcoming Appointments", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        AppointmentCardPatient()

        Spacer(modifier = Modifier.height(16.dp))

        Text("Prescriptions", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        PrescriptionCard()
    }
}

@Composable
fun MedicalRecordCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Recent Visit: Dr. Smith", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Diagnosis: Seasonal Allergies", fontSize = 14.sp, color = Color.Gray)
            Spacer(modifier = Modifier.height(8.dp))
            Text("Notes: Continue antihistamines and monitor symptoms.", fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Composable
fun AppointmentCardPatient() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.MedicalServices, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Dental Cleaning", fontWeight = FontWeight.Bold)
                    Text("With Dr. Adams", color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFDFF5E1), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Confirmed", fontSize = 12.sp, color = Color(0xFF34A853))
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("10 May, 2025")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("11:00 AM")
                }
            }
        }
    }
}

@Composable
fun PrescriptionCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Cetirizine 10mg", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Take one tablet daily for allergies.", fontSize = 14.sp, color = Color.Gray)
        }
    }
}
@Composable
fun BottomNavigationBar(selectedTab: Int, onTabSelected: (Int) -> Unit) {
    val items = listOf(
        Pair("Home", Icons.Default.Home),
        Pair("Messages", Icons.Default.Email),
        Pair("Schedule", Icons.Default.CalendarToday),
        Pair("Profile", Icons.Default.Person)
    )

    NavigationBar {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedTab == index,
                onClick = { onTabSelected(index) },
                icon = { Icon(item.second, contentDescription = item.first) },
                label = { Text(item.first) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF1976D2),
                    selectedTextColor = Color(0xFF1976D2),
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFFE3F2FD)
                )
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewPatientHomeScreen() {
    val navController = rememberNavController()
    PatientHomeScreen(navController)
}
