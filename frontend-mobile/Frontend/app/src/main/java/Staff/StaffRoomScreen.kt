@file:OptIn(ExperimentalMaterial3Api::class)

package Staff

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun StaffRoomScreen(navController: NavController? = null) {
    val primaryColor = Color(0xFF1976D2)
    val backgroundColor = Color(0xFFF9F9F9)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Room Overview") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            Text("Available Rooms", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            RoomCard(
                roomNumber = "101",
                service = "General Ward",
                availability = "Available",
                occupancy = "2/4",
                statusColor = Color(0xFFC8E6C9)
            )

            RoomCard(
                roomNumber = "102",
                service = "ICU",
                availability = "Not Available",
                occupancy = "4/4",
                statusColor = Color(0xFFFFCDD2)
            )

            RoomCard(
                roomNumber = "103",
                service = "Surgical Ward",
                availability = "Available",
                occupancy = "1/3",
                statusColor = Color(0xFFFFF9C4)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text("Room Requests", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            RoomCard(
                roomNumber = "104",
                service = "Private Room",
                availability = "Pending Request",
                occupancy = "1/1",
                statusColor = Color(0xFFBBDEFB)
            )
        }
    }
}

@Composable
fun RoomCard(
    roomNumber: String,
    service: String,
    availability: String,
    occupancy: String,
    statusColor: Color
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Home, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Room #$roomNumber", fontWeight = FontWeight.Bold)
                    Text(service, color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(statusColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(availability, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Group, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Occupancy: $occupancy")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Request Status: $availability")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                if (availability == "Available") {
                    Button(onClick = { /* TODO: Implement booking or room request logic */ }) {
                        Text("Request Room")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStaffRoomScreen() {
    StaffRoomScreen()
}
