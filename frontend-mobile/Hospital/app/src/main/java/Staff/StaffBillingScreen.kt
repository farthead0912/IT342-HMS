@file:OptIn(ExperimentalMaterial3Api::class)

package Staff

import Staff.viewmodel.BillingViewModel
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import Staff.data.BillingDTO
import Staff.data.BillingRepository
import Staff.data.Patient
import Staff.data.Room
import Staff.data.Staff
import Staff.viewmodel.BillingViewModelFactory

@Composable
fun StaffBillingScreen(navController: NavController? = null) {
    val primaryColor = Color(0xFF1976D2)
    val backgroundColor = Color(0xFFF9F9F9)

    val billingRepository = remember { BillingRepository(userRole = "STAFF") }
    val billingViewModel: BillingViewModel = viewModel(
        factory = BillingViewModelFactory(
            userRole = "STAFF",
            repository = billingRepository
        )
    )
    val billingList by billingViewModel.billingList.collectAsState()
    val showDialog by billingViewModel.showDialog.collectAsState()
    val selectedBilling by billingViewModel.selectedBilling.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        billingViewModel.getAllBills()
    }

    // Filtered billing list based on search query
    val filteredBillingList = billingList.filter { it.billId.toString().contains(searchQuery, ignoreCase = true) }

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { billingViewModel.openDialog(null) },
                containerColor = primaryColor
            ) {
                Icon(Icons.Default.Add, contentDescription = "Add Billing")
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
            // Search bar for filtering billing records
            OutlinedTextField(
                value = searchQuery,
                onValueChange = {
                    searchQuery = it // Update search query on text change
                },
                label = { Text("Search Billing ID") },
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { } // This makes the search bar interactive.
                    .padding(8.dp),  // Optional: Adds padding around the search bar
                leadingIcon = { Icon(Icons.Default.Search, contentDescription = "Search") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("Billing Records", fontWeight = FontWeight.Bold, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(8.dp))

            // Display filtered billing records
            filteredBillingList.forEach { billing ->
                BillingCard(
                    billing = billing,
                    onClick = { billingViewModel.openDialog(billing) }
                )
            }
        }
    }

    if (showDialog) {
        BillingDialog(
            billing = selectedBilling,
            onDismiss = { billingViewModel.closeDialog() },
            onSave = { bill ->
                if (selectedBilling == null) {
                    billingViewModel.createBill(bill)
                } else {
                    billingViewModel.updateBill(bill)
                }
            }
        )
    }
}


@Composable
fun BillingCard(billing: BillingDTO, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text("Bill ID: ${billing.billId}", fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(4.dp))
            Text("Patient ID: ${billing.patient.patientId}")
            Text("Staff ID: ${billing.staff.staffId}")
            Text("Room ID: ${billing.room.roomId}")
            Text("Amount: $${billing.amount}")
            Text("Issued At: ${billing.issuedAt}")
        }
    }
}

@Composable
fun BillingDialog(
    billing: BillingDTO?,
    onDismiss: () -> Unit,
    onSave: (BillingDTO) -> Unit
) {
    var patientId by remember { mutableStateOf(billing?.patient?.patientId?.toString() ?: "") }
    var staffId by remember { mutableStateOf(billing?.staff?.staffId?.toString() ?: "") }
    var roomId by remember { mutableStateOf(billing?.room?.roomId?.toString() ?: "") }
    var amount by remember { mutableStateOf(billing?.amount?.toString() ?: "") }
    var issuedAt by remember { mutableStateOf(billing?.issuedAt ?: "") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(if (billing == null) "Add Billing" else "Edit Billing") },
        text = {
            Column {
                OutlinedTextField(
                    value = patientId,
                    onValueChange = { patientId = it },
                    label = { Text("Patient ID") }
                )
                OutlinedTextField(
                    value = staffId,
                    onValueChange = { staffId = it },
                    label = { Text("Staff ID") }
                )
                OutlinedTextField(
                    value = roomId,
                    onValueChange = { roomId = it },
                    label = { Text("Room ID") }
                )
                OutlinedTextField(
                    value = amount,
                    onValueChange = { amount = it },
                    label = { Text("Amount") }
                )
                OutlinedTextField(
                    value = issuedAt,
                    onValueChange = { issuedAt = it },
                    label = { Text("Issued At (YYYY-MM-DD)") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val newBilling = BillingDTO(
                    billId = billing?.billId ?: 0,
                    patient = Patient(patientId.toIntOrNull() ?: 0),
                    staff = Staff(staffId.toIntOrNull() ?: 0),
                    room = Room(roomId.toIntOrNull() ?: 0),
                    amount = amount.toDoubleOrNull() ?: 0.0,
                    issuedAt = issuedAt
                )
                onSave(newBilling)
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            OutlinedButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    )
}
