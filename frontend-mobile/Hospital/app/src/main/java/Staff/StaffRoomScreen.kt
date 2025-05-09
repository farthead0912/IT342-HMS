@file:OptIn(ExperimentalMaterial3Api::class)

package Staff

import Staff.data.RoomDTO
import Staff.viewmodel.RoomViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.launch
import Staff.viewmodel.RoomViewModel.RoomState
import Staff.viewmodel.RoomViewModelFactory

@Composable
fun StaffRoomScreen(
    navController: NavController? = null,
    viewModel: RoomViewModel = viewModel(factory = RoomViewModelFactory("STAFF"))
) {
    val roomState by viewModel.roomState.collectAsState()
    val backgroundColor = Color(0xFFF9F9F9)
    val coroutineScope = rememberCoroutineScope()

    // Fetch rooms when the screen is first loaded


    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Patient Rooms") },
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
                .padding(16.dp)
        ) {
            when (roomState) {
                is RoomState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }

                is RoomState.Error -> {
                    val message = (roomState as RoomState.Error).message
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text(text = message, color = Color.Red)
                    }
                }

                is RoomState.RoomList -> {
                    val rooms = (roomState as RoomState.RoomList).rooms
                    if (rooms.isEmpty()) {
                        Text("No rooms available", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    } else {
                        Text("Available Rooms", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        Spacer(modifier = Modifier.height(8.dp))
                        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                            rooms.forEach { room ->
                                PatientRoomCard(room = room)
                            }
                        }
                    }
                }

                else -> {
                    // Idle or Success state - show nothing
                }
            }
        }
    }
}

@Composable
fun PatientRoomCard(room: RoomDTO) {
    val statusColor = when {
        !room.isOccupied -> Color(0xFFC8E6C9) // Available (Greenish)
        room.isOccupied -> Color(0xFFFFCDD2)   // Not Available (Reddish)
        else -> Color(0xFFFFF9C4)             // Default (Yellowish)
    }

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
                    Text("Room #${room.roomNumber}", fontWeight = FontWeight.Bold)
                    Text(room.roomType, color = Color.Gray, fontSize = 12.sp)
                }
                Spacer(modifier = Modifier.weight(1f))
                Box(
                    modifier = Modifier
                        .background(statusColor, shape = RoundedCornerShape(8.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        if (room.isOccupied) "Occupied" else "Available",
                        fontSize = 12.sp
                    )
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
                    Text("Floor: ${room.floorNumber}")
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.DateRange, contentDescription = null, tint = Color.Gray)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text("Room Price: \$${room.roomPrice}")
                }
            }
        }
    }
}
