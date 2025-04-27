package Staff

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.background

@Composable
fun StaffMessageScreen(navController: NavController) {
    val primaryColor = Color(0xFF1976D2)  // Primary color for consistency
    val lightGray = Color(0xFFF9F9F9)

    var selectedTab by remember { mutableStateOf(1) } // Set the Messages tab as selected

    Scaffold(
        containerColor = lightGray,
        bottomBar = {
            BottomNavigationBar(
                selectedTab = selectedTab,
                onTabSelected = {
                    selectedTab = it
                    when (it) {
                        0 -> navController.navigate("StaffHomeScreen") {
                            popUpTo("StaffMessageScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        1 -> {}

                        2 -> navController.navigate("StaffScheduleScreen"){
                            popUpTo("StaffMessageScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        3 -> navController.navigate("StaffProfileScreen") {
                            popUpTo("StaffScheduleScreen") { inclusive = true }
                            launchSingleTop = true
                    }
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
        ) {
            // Messages Section
            SectionTitle1("Messages")

            // Displaying a List of Messages
            MessageItem("Dr. Sarah Lee", "New shift schedule available", "3 hours ago")
            MessageItem("Nurse Mark", "Patient discharge notification", "1 day ago")
            MessageItem("Admin", "Reminder: Staff meeting at 3 PM", "2 days ago")
            MessageItem("Dr. Adam Brown", "Patient admission updates", "3 days ago")

            Spacer(modifier = Modifier.height(24.dp))

            // Navigation Button (for consistency with Home Screen)
            Button(
                onClick = { /* Handle some action */ },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.Message, contentDescription = "Messages", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("View All Messages", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun SectionTitle1(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun MessageItem(sender: String, subject: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Profile Picture (Circle)
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color.Gray, CircleShape)
                    .padding(6.dp),
                contentAlignment = Alignment.Center
            ) {
                // Placeholder image
                Image(painter = painterResource(id = android.R.drawable.ic_menu_camera), contentDescription = "Sender Image")
            }
            Spacer(modifier = Modifier.width(16.dp))

            // Message Content
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(sender, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text(subject, fontSize = 14.sp)
            }

            // Time
            Text(time, fontSize = 12.sp, color = Color.Gray)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStaffMessageScreen() {
    val navController = rememberNavController()
    StaffMessageScreen(navController)
}
