package Staff.viewmodel

import Staff.data.BillingRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class BillingViewModelFactory(
    private val userRole: String,
    private val repository: BillingRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BillingViewModel::class.java)) {
            return BillingViewModel(userRole = userRole, repository = repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}




