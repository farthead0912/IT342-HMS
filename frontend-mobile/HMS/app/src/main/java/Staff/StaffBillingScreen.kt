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
import androidx.navigation.compose.rememberNavController

@Composable
fun StaffBillingScreen(navController: NavController? = null) {
    val primaryColor = Color(0xFF1976D2)
    val backgroundColor = Color(0xFFF9F9F9)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Billing Overview") },
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
            Text("Pending Bills", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            BillingCard(
                name = "John Doe",
                service = "MRI Scan",
                amount = "$1,200",
                dueDate = "20 Apr 2025",
                status = "Unpaid",
                statusColor = Color(0xFFFFCDD2)
            )

            BillingCard(
                name = "Jane Smith",
                service = "Blood Test",
                amount = "$250",
                dueDate = "18 Apr 2025",
                status = "Partially Paid",
                statusColor = Color(0xFFFFF9C4)
            )

            BillingCard(
                name = "Ali Rahman",
                service = "Surgery Charges",
                amount = "$5,600",
                dueDate = "25 Apr 2025",
                status = "Paid",
                statusColor = Color(0xFFC8E6C9)
            )

            Spacer(modifier = Modifier.height(24.dp))
            Text("Insurance Claims", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            BillingCard(
                name = "Farah Khan",
                service = "ICU Stay",
                amount = "$3,400",
                dueDate = "Pending Approval",
                status = "Under Insurance Review",
                statusColor = Color(0xFFBBDEFB)
            )
        }
    }
}

@Composable
fun BillingCard(
    name: String,
    service: String,
    amount: String,
    dueDate: String,
    status: String,
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
                Icon(Icons.Default.Person, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(name, fontWeight = FontWeight.Bold)
                    Text(service, color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(statusColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(status, fontSize = 12.sp)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.AttachMoney, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(amount)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(dueDate)
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                if (status != "Paid") {
                    Button(onClick = { /* TODO: Implement payment logic */ }) {
                        Text("Mark as Paid")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStaffBillingScreen() {
    StaffBillingScreen()
}