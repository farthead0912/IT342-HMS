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
fun StaffInventoryScreen(navController: NavController? = null) {
    val backgroundColor = Color(0xFFF9F9F9)

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Inventory Overview") },
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
            Text("Inventory Items", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            InventoryCard(
                itemName = "Surgical Masks",
                quantity = "500 pcs",
                category = "PPE",
                status = "Sufficient",
                statusColor = Color(0xFFC8E6C9)
            )

            InventoryCard(
                itemName = "Hand Sanitizer",
                quantity = "12 bottles",
                category = "Hygiene",
                status = "Low Stock",
                statusColor = Color(0xFFFFF9C4)
            )

            InventoryCard(
                itemName = "Ventilator",
                quantity = "4 units",
                category = "Equipment",
                status = "Critical",
                statusColor = Color(0xFFFFCDD2)
            )

            InventoryCard(
                itemName = "Gloves",
                quantity = "800 pairs",
                category = "PPE",
                status = "Sufficient",
                statusColor = Color(0xFFC8E6C9)
            )
        }
    }
}

@Composable
fun InventoryCard(
    itemName: String,
    quantity: String,
    category: String,
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
                Icon(Icons.Default.Inventory, contentDescription = null, tint = Color(0xFF1976D2))
                Spacer(modifier = Modifier.width(8.dp))
                Column {
                    Text(itemName, fontWeight = FontWeight.Bold)
                    Text(category, color = Color.Gray, fontSize = 12.sp)
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
                    Icon(Icons.Default.FormatListNumbered, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Quantity: $quantity")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(horizontalArrangement = Arrangement.End, modifier = Modifier.fillMaxWidth()) {
                if (status == "Low Stock" || status == "Critical") {
                    Button(onClick = { /* TODO: Implement restock request logic */ }) {
                        Text("Request Restock")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewStaffInventoryScreen() {
    StaffInventoryScreen()
}
