package Staff.data


data class StaffDTO(
    val staffId: Int,
    val firstName: String,
    val lastName: String,
    val position: String,
    val departmentId: Int,
    val userId: Int
)
