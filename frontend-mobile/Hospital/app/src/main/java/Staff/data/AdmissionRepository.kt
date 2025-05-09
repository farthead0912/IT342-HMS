package Staff.repository

import Staff.data.Admission
import Staff.data.AdmissionResponse
import Staff.data.AdmissionsListResponse
import Staff.network.AdmissionService
import retrofit2.Response

class AdmissionRepository(private val admissionService: AdmissionService) {

    suspend fun getAdmissionById(admissionId: Int): Response<AdmissionResponse> {
        return admissionService.getAdmissionById(admissionId)
    }

    suspend fun getAdmissionsByPatientId(patientId: Int): Response<AdmissionsListResponse> {
        return admissionService.getAdmissionsByPatientId(patientId)
    }

    suspend fun createAdmission(admission: Admission): Response<AdmissionResponse> {
        return admissionService.createAdmission(admission)
    }

    suspend fun updateAdmission(admissionId: Int, admission: Admission): Response<AdmissionResponse> {
        return admissionService.updateAdmission(admissionId, admission)
    }

    suspend fun deleteAdmission(admissionId: Int): Response<Void> {
        return admissionService.deleteAdmission(admissionId)
    }
}
