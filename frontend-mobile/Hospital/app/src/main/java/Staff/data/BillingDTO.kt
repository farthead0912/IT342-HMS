package Staff.data

import java.util.Date

data class BillingDTO(
    val billId: Int,
    val patient: Patient,
    val staff: Staff,
    val room: Room,
    val amount: Double,
    val issuedAt: String
)


data class Patient(val patientId: Int)
data class Staff(val staffId: Int)
data class Room(val roomId: Int)

data class BillingResponse(
    val message: String,
    val status: String
)

