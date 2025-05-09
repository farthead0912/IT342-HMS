
package Doctor


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
import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun DoctorProfileScreen(navController: NavController) {
    val primaryColor = Color(0xFF1976D2)  // Defining primaryColor here
    val lightGray = Color(0xFFF9F9F9)

    var selectedTab by remember { mutableStateOf(3) } // Set the Profile tab as selected

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
                            popUpTo("StaffProfileScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        2 -> navController.navigate("StaffScheduleScreen"){
                            popUpTo("StaffProfileScreen") { inclusive = true }
                            launchSingleTop = true
                        }
                        3 -> {}
                    }
                }
            )
        }
    )


    { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(paddingValues)
        ) {
            // Profile Header
            ProfileHeader(primaryColor)
            Spacer(modifier = Modifier.height(16.dp))

            // Personal Info Section
            SectionTitle("Personal Info")
            ProfileInfoItem("Name", "Dr. John Doe")
            ProfileInfoItem("Specialty", "Cardiology")
            ProfileInfoItem("Email", "john.doe@example.com")
            ProfileInfoItem("Phone", "+123 456 7890")
            Spacer(modifier = Modifier.height(16.dp))

            // Work Info Section
            SectionTitle("Work Info")
            ProfileInfoItem("Department", "Cardiology")
            ProfileInfoItem("Room", "Room 201")
            ProfileInfoItem("Work Hours", "9:00 AM - 5:00 PM")

            Spacer(modifier = Modifier.height(24.dp))

            // Log Out Button
            Button(
                onClick = {
                    // Handle Log Out Action, you can navigate to a login screen or clear user session
                    navController.navigate("login") {
                        popUpTo("StaffProfileScreen") { inclusive = true }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = primaryColor),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Icon(Icons.Default.ExitToApp, contentDescription = "Log Out", tint = Color.White)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("Log Out", color = Color.White, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ProfileHeader(primaryColor: Color) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(8.dp),
            colors = CardDefaults.cardColors(containerColor = primaryColor),
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Profile Picture (Circle)
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .background(Color.Gray, CircleShape)
                        .padding(6.dp),
                    contentAlignment = Alignment.Center
                ) {
                    // Placeholder image, replace with actual image later
                    Image(painter = painterResource(id = android.R.drawable.ic_menu_camera), contentDescription = "Profile Picture")
                }
                Spacer(modifier = Modifier.width(16.dp))

                Column {
                    Text("Dr. John Doe", color = Color.White, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text("Cardiologist", color = Color.White, fontSize = 14.sp)
                }
            }
        }
    }
}

@Composable
fun SectionTitle(title: String) {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        modifier = Modifier.padding(bottom = 8.dp)
    )
}

@Composable
fun ProfileInfoItem(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(label, color = Color.Gray, fontSize = 14.sp)
        Text(value, fontWeight = FontWeight.Bold, fontSize = 14.sp)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewDoctorProfileScreen() {
    val navController = rememberNavController()
    DoctorProfileScreen(navController)
}