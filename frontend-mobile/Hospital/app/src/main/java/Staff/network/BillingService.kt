package Staff.network

import Staff.data.BillingDTO
import Staff.data.BillingResponse
import retrofit2.Response
import retrofit2.http.*

interface BillingService {

    @GET("/api/billing/")
    suspend fun getBillings(): Response<List<BillingDTO>>

    @GET("/api/billing/{billingId}")
    suspend fun getBillingById(@Path("billingId") billingId: Int): Response<BillingDTO>

    @POST("/api/billing/")
    suspend fun createBilling(@Body billing: BillingDTO): Response<BillingResponse>

    @PUT("/api/billing/{billingId}")
    suspend fun updateBilling(
        @Path("billingId") billingId: Int,
        @Body updatedBilling: BillingDTO
    ): Response<BillingResponse>

    @DELETE("/api/billing/{billingId}")
    suspend fun deleteBilling(@Path("billingId") billingId: Int): Response<BillingResponse>
}
