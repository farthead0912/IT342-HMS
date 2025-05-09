package Staff.viewmodel

import Staff.data.Admission
import Staff.repository.AdmissionRepository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class AdmissionViewModel(private val repository: AdmissionRepository) : ViewModel() {

    private val _admission = MutableLiveData<Admission?>()
    val admission: LiveData<Admission?> = _admission

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error

    // Create a new admission
    fun createAdmission(admission: Admission) {
        viewModelScope.launch {
            try {
                val response = repository.createAdmission(admission)
                if (response.isSuccessful) {
                    _admission.value = response.body()?.data
                } else {
                    _error.value = "Failed to create admission. ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }

    // Update an admission
    fun updateAdmission(admissionId: Int, admission: Admission) {
        viewModelScope.launch {
            try {
                val response = repository.updateAdmission(admissionId, admission)
                if (response.isSuccessful) {
                    _admission.value = response.body()?.data
                } else {
                    _error.value = "Failed to update admission. ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Error: ${e.message}"
            }
        }
    }
}
