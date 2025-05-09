package Staff.data

import com.example.hospital.network.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BillingRepository(private val userRole: String) {

    private val billingService = RetrofitClient.billingService

    suspend fun createBill(bill: BillingDTO): Result<String> {
        return try {
            val response = billingService.createBilling(bill)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Bill created")
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to create bill"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getAllBills(): Result<List<BillingDTO>> {
        return try {
            val response = billingService.getBillings()
            if (response.isSuccessful) {
                Result.success(response.body() ?: emptyList())
            } else {
                Result.failure(Exception("Failed to fetch bills"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteBill(billId: Int): Result<String> {
        if (userRole == "STAFF") {
            return Result.failure(Exception("Staff cannot delete billing records"))
        }

        return try {
            val response = billingService.deleteBilling(billId)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Bill deleted")
            } else {
                Result.failure(Exception("Failed to delete bill"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateBill(updatedBilling: BillingDTO): Result<String> {
        return try {
            // Use the billingId from updatedBilling object
            val response = billingService.updateBilling(updatedBilling.billId, updatedBilling)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Bill updated")
            } else {
                Result.failure(Exception(response.errorBody()?.string() ?: "Failed to update bill"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
