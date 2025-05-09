package Patient.data

data class PatientDTO(
    val patientId: Int,                     // Unique ID for the patient
    val firstName: String,                  // Patient's first name
    val lastName: String,                   // Patient's last name
    val age: Int,                           // Patient's age
    val gender: String,                     // Patient's gender
    val bloodType: String,                  // Patient's blood type
    val roomId: Int,                        // Room assigned to the patient (initially null until assigned)
    val userId: Int,                        // User ID of the patient (if user system is linked)
    val patientRecordIds: List<Int>,        // List of medical records linked to this patient
    val admissionIds: List<Int>,            // List of admissions this patient has undergone
    val assignedEquipmentIds: List<Int>     // List of equipment assigned to this patient
)
