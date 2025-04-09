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


@Composable
fun StaffScheduleScreen(navController: NavController) {
    val primaryColor = Color(0xFF1976D2)
    val lightGray = Color(0xFFF9F9F9)

    var selectedTab by remember { mutableStateOf(2) }

    Scaffold(
        containerColor = lightGray,
        bottomBar = {
            BottomNavigationBar(selectedTab = selectedTab, onTabSelected = { selectedTab = it })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            Text("Your Schedule", fontSize = 24.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))

            // Today's Schedule Header
            Text("Today's Appointments", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))

            // Sample appointments
            AppointmentCard("Dr. John Doe", "Cardiologist", "10:00 AM", "Room 301")
            AppointmentCard("Dr. Jane Smith", "Dermatologist", "11:30 AM", "Room 205")
            AppointmentCard("Dr. Alex Ray", "Orthopedic", "2:00 PM", "Room 402")

            Spacer(modifier = Modifier.height(24.dp))

            // Work Shifts
            Text("Work Shifts", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))

            WorkShiftCard("Morning Shift", "8:00 AM - 4:00 PM")
            WorkShiftCard("Evening Shift", "4:00 PM - 12:00 AM")

            Spacer(modifier = Modifier.height(24.dp))

            // Notes
            Text("Additional Notes", fontWeight = FontWeight.SemiBold)
            Spacer(modifier = Modifier.height(8.dp))

            // Improved design for Additional Notes
            NoteCard("Ensure to take a lunch break between 12:00 PM and 1:00 PM.")
            NoteCard("Please confirm the availability of supplies for the 2:00 PM appointment.")
        }
    }
}

// Appointment Card
@Composable
fun AppointmentCard(doctorName: String, specialty: String, time: String, room: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(doctorName, fontWeight = FontWeight.Bold)
                    Text(specialty, color = Color.Gray, fontSize = 12.sp)
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(time)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.LocationOn, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(room)
                }
            }
        }
    }
}

// Work Shift Card
@Composable
fun WorkShiftCard(shiftName: String, shiftTime: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(shiftName, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text(shiftTime, color = Color.Gray, fontSize = 12.sp)
        }
    }
}

// Note Card for Additional Notes Section
@Composable
fun NoteCard(note: String) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(note, fontSize = 14.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStaffScheduleScreen() {
    val navController = rememberNavController()
    StaffScheduleScreen(navController)
}
