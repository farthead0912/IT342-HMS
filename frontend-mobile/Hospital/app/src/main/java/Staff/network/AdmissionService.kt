package Staff.network

import Staff.data.Admission
import Staff.data.AdmissionResponse
import Staff.data.AdmissionsListResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface AdmissionService {

    @GET("api/admission/")
    suspend fun getAdmissions(): Response<AdmissionsListResponse>

    @GET("api/admission/{admissionId}")
    suspend fun getAdmissionById(@Path("admissionId") admissionId: Int): Response<AdmissionResponse>

    @GET("api/admission/patient/{patientId}")
    suspend fun getAdmissionsByPatientId(@Path("patientId") patientId: Int): Response<AdmissionsListResponse>

    @POST("api/admission/")
    suspend fun createAdmission(@Body admission: Admission): Response<AdmissionResponse>

    @PUT("api/admission/{admissionId}")
    suspend fun updateAdmission(@Path("admissionId") admissionId: Int, @Body admission: Admission): Response<AdmissionResponse>

    @DELETE("api/admission/{admissionId}")
    suspend fun deleteAdmission(@Path("admissionId") admissionId: Int): Response<Void>

}
