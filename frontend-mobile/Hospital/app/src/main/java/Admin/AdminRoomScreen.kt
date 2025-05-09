@file:OptIn(ExperimentalMaterial3Api::class)

package Admin

import androidx.compose.foundation.background
import Staff.data.RoomDTO
import Staff.viewmodel.RoomViewModel
import Staff.viewmodel.RoomViewModelFactory
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AdminRoomScreen(navController: NavController? = null, userRole: String) {
    val primaryColor = Color(0xFF1976D2)
    val backgroundColor = Color(0xFFF9F9F9)

    val roomViewModel: RoomViewModel = viewModel(factory = RoomViewModelFactory(userRole))
    val roomState = roomViewModel.roomState.collectAsState()

    var searchRoomId by remember { mutableStateOf("") }
    var showDialog by remember { mutableStateOf(false) }

    // Fields for Dialog
    var roomNumber by remember { mutableStateOf("") }
    var roomType by remember { mutableStateOf("") }
    var roomPrice by remember { mutableStateOf("") }
    var floorNumber by remember { mutableStateOf("") }
    var isOccupied by remember { mutableStateOf(false) }
    var patientId by remember { mutableStateOf("") }
    var staffId by remember { mutableStateOf("") }

    // To hold the room being edited
    var roomToEdit by remember { mutableStateOf<RoomDTO?>(null) }

    LaunchedEffect(Unit) {
        roomViewModel.getAllRooms()
    }

    Scaffold(
        containerColor = backgroundColor,
        topBar = {
            TopAppBar(
                title = { Text("Room Management") },
                navigationIcon = {
                    IconButton(onClick = { navController?.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.White)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showDialog = true
                    roomToEdit = null
                    // Reset fields when adding new room
                    roomNumber = ""
                    roomType = ""
                    roomPrice = ""
                    floorNumber = ""
                    isOccupied = false
                    patientId = ""
                    staffId = ""
                },
                containerColor = primaryColor
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Room")
            }
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {
            // Search Section
            OutlinedTextField(
                value = searchRoomId,
                onValueChange = { searchRoomId = it },
                label = { Text("Search by Room ID") },
                trailingIcon = {
                    IconButton(onClick = {
                        if (searchRoomId.isNotEmpty()) {
                            roomViewModel.getRoomById(searchRoomId.toIntOrNull() ?: 0)
                        }
                    }) {
                        Icon(Icons.Default.Search, contentDescription = "Search")
                    }
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Search
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Room List Section
            when (val state = roomState.value) {
                is RoomViewModel.RoomState.Loading -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        CircularProgressIndicator()
                    }
                }
                is RoomViewModel.RoomState.RoomList -> {
                    if (state.rooms.isEmpty()) {
                        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                            Text("No rooms found")
                        }
                    } else {
                        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                            state.rooms.forEach { room ->
                                AdminRoomCard(
                                    room = room,
                                    onDelete = { roomViewModel.deleteRoom(room.roomId) },
                                    onUpdate = {
                                        roomToEdit = room
                                        roomNumber = room.roomNumber.toString()
                                        roomType = room.roomType
                                        roomPrice = room.roomPrice.toString()
                                        floorNumber = room.floorNumber.toString()
                                        isOccupied = room.isOccupied
                                        patientId = room.patientId?.toString() ?: ""
                                        staffId = room.staffId?.toString() ?: ""
                                        showDialog = true
                                    }
                                )
                            }
                        }
                    }
                }
                is RoomViewModel.RoomState.Error -> {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                        Text("No rooms: ${state.message}", color = Color.Red)
                    }
                }
                else -> {}
            }
        }
    }

    // Add/Edit Room Dialog
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            shape = RoundedCornerShape(16.dp),
            title = { Text(if (roomToEdit == null) "Add New Room" else "Edit Room") },
            text = {
                Column {
                    OutlinedTextField(
                        value = roomNumber,
                        onValueChange = { roomNumber = it },
                        label = { Text("Room Number *") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = roomType,
                        onValueChange = { roomType = it },
                        label = { Text("Room Type *") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = roomPrice,
                        onValueChange = { roomPrice = it },
                        label = { Text("Price per Night *") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = floorNumber,
                        onValueChange = { floorNumber = it },
                        label = { Text("Floor Number *") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Checkbox(
                            checked = isOccupied,
                            onCheckedChange = { isOccupied = it }
                        )
                        Text("Occupied")
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = patientId,
                        onValueChange = { patientId = it },
                        label = { Text("Patient ID (Optional)") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = staffId,
                        onValueChange = { staffId = it },
                        label = { Text("Staff ID (Optional)") },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Done
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        val room = RoomDTO(
                            roomId = roomToEdit?.roomId ?: 0,
                            roomNumber = roomNumber.toIntOrNull() ?: 0,
                            roomType = roomType,
                            roomPrice = roomPrice.toDoubleOrNull() ?: 0.0,
                            floorNumber = floorNumber.toIntOrNull() ?: 0,
                            isOccupied = isOccupied,
                            patientId = patientId.toIntOrNull(),
                            staffId = staffId.toIntOrNull(),
                            admissionIdList = roomToEdit?.admissionIdList ?: emptyList()
                        )

                        if (roomToEdit == null) {
                            roomViewModel.createRoom(room)
                        } else {
                            roomViewModel.updateRoom(roomToEdit!!.roomId, room)
                        }
                        showDialog = false
                    },
                    enabled = roomNumber.isNotEmpty() && roomType.isNotEmpty() &&
                            roomPrice.isNotEmpty() && floorNumber.isNotEmpty()
                ) {
                    Text(if (roomToEdit == null) "Add Room" else "Update Room")
                }
            },
            dismissButton = {
                OutlinedButton(onClick = { showDialog = false }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Composable
fun AdminRoomCard(
    room: RoomDTO,
    onDelete: () -> Unit,
    onUpdate: () -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Header with room number and status
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Room",
                    tint = Color(0xFF1976D2),
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(12.dp))
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = "Room #${room.roomNumber}",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp
                    )
                    Text(
                        text = "Floor ${room.floorNumber} • ${room.roomType}",
                        color = Color.Gray,
                        fontSize = 14.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .background(
                            color = if (room.isOccupied) Color(0xFFFFCDD2) else Color(0xFFC8E6C9),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                ) {
                    Text(
                        text = if (room.isOccupied) "Occupied" else "Available",
                        color = if (room.isOccupied) Color(0xFFC62828) else Color(0xFF2E7D32),
                        fontSize = 12.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Details section
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                // Price and Occupancy
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    RoomInfoItem(
                        icon = Icons.Default.AttachMoney,
                        label = "Price",
                        value = "$${"%.2f".format(room.roomPrice)}/night"
                    )
                    RoomInfoItem(
                        icon = Icons.Default.People,
                        label = "Occupants",
                        value = "${room.admissionIdList.size}"
                    )
                }

                // Patient and Staff info if available
                if (room.patientId != null) {
                    RoomInfoItem(
                        icon = Icons.Default.Person,
                        label = "Patient ID",
                        value = room.patientId.toString(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                if (room.staffId != null) {
                    RoomInfoItem(
                        icon = Icons.Default.MedicalServices,
                        label = "Staff ID",
                        value = room.staffId.toString(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Action buttons
            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                OutlinedButton(
                    onClick = onUpdate,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color(0xFF1976D2)
                    ),
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Edit")
                }
                OutlinedButton(
                    onClick = onDelete,
                    colors = ButtonDefaults.outlinedButtonColors(
                        contentColor = Color.Red
                    )
                ) {
                    Text("Delete")
                }
            }
        }
    }
}

@Composable
private fun RoomInfoItem(
    icon: ImageVector,
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = Color.Gray,
            modifier = Modifier.size(20.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(
                text = label,
                color = Color.Gray,
                fontSize = 12.sp
            )
            Text(
                text = value,
                fontWeight = FontWeight.Medium,
                fontSize = 14.sp
            )
        }
    }
}