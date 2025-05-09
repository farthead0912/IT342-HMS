package Staff.data


data class RoomDTO(
    val roomId: Int = 0,          // Provide default values
    val roomType: String = "",
    val roomNumber: Int = 0,
    val roomPrice: Double = 0.0,
    val floorNumber: Int = 0,
    val isOccupied: Boolean = false,
    val patientId: Int? = null,
    val staffId: Int? = null,
    val admissionIdList: List<Int> = emptyList()
)



data class RoomResponse(
    val message: String,
    val status: String
)
