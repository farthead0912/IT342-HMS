package Staff.data

data class Admission(
    val admissionId: Int = 0, // Default value for new admissions
    val doctorId: Int,
    val patientId: Int,
    val roomId: Int,
    val admissionDate: String,
    val dischargeDate: String?,
    val admissionReason: String,
    val status: String
)

data class AdmissionResponse(
    val success: Boolean,
    val message: String,
    val data: Admission?
)

data class AdmissionsListResponse(
    val success: Boolean,
    val message: String,
    val data: List<Admission>
)
