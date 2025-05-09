package Doctor

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun DoctorHomeScreen(navController: NavController) {
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
                0 -> HomeContent(navController)
                1 -> navController.navigate("DoctorMessageScreen") {
                    popUpTo("DoctorHomeScreen") { inclusive = true }
                    launchSingleTop = true }
                2 -> navController.navigate("DoctorScheduleScreen") {
                    popUpTo("DoctorHomeScreen") { inclusive = true }
                    launchSingleTop = true }
                3 -> navController.navigate("DoctorProfileScreen") {
                    popUpTo("DoctorHomeScreen") { inclusive = true }
                    launchSingleTop = true }
            }
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    val primaryColor = Color(0xFF1976D2)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        // Greeting
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Good Morning, Dr. Smith", fontSize = 22.sp, fontWeight = FontWeight.Bold)
                Text("Your shift starts at 8:00 AM", fontSize = 14.sp, color = Color.Gray)
            }
            Icon(Icons.Default.Notifications, contentDescription = null, tint = primaryColor)
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Search Bar
        OutlinedTextField(
            value = "",
            onValueChange = {},
            placeholder = { Text("Search patients, tasks...") },
            leadingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = Color.Transparent,
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Quick Actions
        Text("Quick Actions", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            QuickActionItem(Icons.Default.AttachMoney, "Appointments") {
                navController.navigate("DoctorAppointmentScreen")
            }
            QuickActionItem(Icons.Default.MeetingRoom, "Rooms") {
                navController.navigate("DoctorRoomScreen")
            }
            QuickActionItem(Icons.Default.Inventory, "Inventory") {
                navController.navigate("DoctorInventoryScreen")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        DoctorBanner()
        Spacer(modifier = Modifier.height(24.dp))

        Text("Important Tasks", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        AppointmentCard()

        Spacer(modifier = Modifier.height(24.dp))
        Text("Announcements", fontWeight = FontWeight.SemiBold)
        Spacer(modifier = Modifier.height(8.dp))
        AnnouncementCard()
    }
}

@Composable
fun DoctorBanner() {
    Card(
        colors = CardDefaults.cardColors(containerColor = Color(0xFF1976D2)),
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text("Stay updated!", color = Color.White, fontSize = 16.sp)
                Text("New research seminar on patient care\nthis Wednesday at 10 AM", color = Color.White, fontSize = 14.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.White)
                ) {
                    Text("View Details", color = Color(0xFF1976D2))
                }
            }
            Icon(Icons.Default.Info, contentDescription = null, tint = Color.White, modifier = Modifier.size(64.dp))
        }
    }
}

@Composable
fun QuickActionItem(icon: ImageVector, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .size(60.dp)
                .clickable { onClick() },
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
                Icon(icon, contentDescription = label, tint = Color(0xFF1976D2))
            }
        }
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, fontSize = 12.sp)
    }
}

@Composable
fun AppointmentCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text("Morning Rounds", fontWeight = FontWeight.Bold)
                    Text("Ward 7A - Cardiology", color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(Color(0xFFDFF5E1), shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text("Pending", fontSize = 12.sp, color = Color(0xFF34A853))
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
                    Text("22 Apr, 2025")
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AccessTime, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("8:30 AM")
                }
            }
        }
    }
}

@Composable
fun AnnouncementCard() {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Patient Reminder", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Reminder for follow-up appointments tomorrow. Please check the patient list.", fontSize = 14.sp, color = Color.Gray)
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
fun PreviewDoctorHomeScreen() {
    val navController = rememberNavController()
    DoctorHomeScreen(navController)
}
