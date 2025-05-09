package Staff.viewmodel

import Staff.data.BillingDTO
import Staff.data.BillingRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BillingViewModel(
    private val userRole: String,
    private val repository: BillingRepository = BillingRepository(userRole)
) : ViewModel() {

    sealed class BillingState {
        object Idle : BillingState()
        object Loading : BillingState()
        data class Success(val message: String) : BillingState()
        data class Error(val message: String) : BillingState()
        data class BillList(val bills: List<BillingDTO>) : BillingState()
    }

    private val _billingState = MutableStateFlow<BillingState>(BillingState.Idle)
    val billingState: StateFlow<BillingState> = _billingState.asStateFlow()

    private val _billingList = MutableStateFlow<List<BillingDTO>>(emptyList())
    val billingList: StateFlow<List<BillingDTO>> = _billingList.asStateFlow()

    private val _showDialog = MutableStateFlow(false)
    val showDialog: StateFlow<Boolean> = _showDialog.asStateFlow()

    private val _selectedBilling = MutableStateFlow<BillingDTO?>(null)
    val selectedBilling: StateFlow<BillingDTO?> = _selectedBilling.asStateFlow()

    fun openDialog(billing: BillingDTO?) {
        _selectedBilling.value = billing
        _showDialog.value = true
    }

    fun closeDialog() {
        _selectedBilling.value = null
        _showDialog.value = false
    }

    fun createBill(bill: BillingDTO) {
        viewModelScope.launch {
            _billingState.value = BillingState.Loading
            val result = repository.createBill(bill)
            _billingState.value = if (result.isSuccess) {
                BillingState.Success(result.getOrNull() ?: "Bill created")
            } else {
                BillingState.Error(result.exceptionOrNull()?.message ?: "Error creating bill")
            }
        }
    }

    fun getAllBills() {
        viewModelScope.launch {
            _billingState.value = BillingState.Loading
            val result = repository.getAllBills()
            _billingState.value = if (result.isSuccess) {
                BillingState.BillList(result.getOrNull() ?: emptyList())
            } else {
                BillingState.Error(result.exceptionOrNull()?.message ?: "Error fetching bills")
            }
        }
    }

    fun deleteBill(billId: Int) {
        viewModelScope.launch {
            _billingState.value = BillingState.Loading
            val result = repository.deleteBill(billId)
            _billingState.value = if (result.isSuccess) {
                BillingState.Success(result.getOrNull() ?: "Bill deleted")
            } else {
                BillingState.Error(result.exceptionOrNull()?.message ?: "Error deleting bill")
            }
        }
    }

    fun updateBill(updatedBilling: BillingDTO) {
        viewModelScope.launch {
            _billingState.value = BillingState.Loading
            val result = repository.updateBill(updatedBilling)
            if (result.isSuccess) {
                _billingState.value = BillingState.Success(result.getOrNull() ?: "Bill updated")
                getAllBills()  // Refresh list after success
            } else {
                _billingState.value = BillingState.Error(result.exceptionOrNull()?.message ?: "Failed to update billing")
            }
        }
    }
}
