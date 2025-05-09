package Staff.viewmodel

import Staff.data.Admission
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class AdmissionFactory {

    // Method to create a new admission with default values
    fun createAdmission(
        doctorId: Int,
        patientId: Int,
        roomId: Int,
        admissionDate: String = LocalDate.now().format(DateTimeFormatter.ISO_DATE),  // Default to today's date
        dischargeDate: String? = null,
        admissionReason: String,
        status: String = "Pending"  // Default status can be "Pending"
    ): Admission {
        return Admission(
            admissionId = 0,  // Default value for new admissions (to be assigned later, e.g., by the backend)
            doctorId = doctorId,
            patientId = patientId,
            roomId = roomId,
            admissionDate = admissionDate,
            dischargeDate = dischargeDate,
            admissionReason = admissionReason,
            status = status
        )
    }

    // Example method to create a list of admissions (if needed)
    fun createAdmissionsList(vararg admissions: Admission): List<Admission> {
        return admissions.toList()
    }
}