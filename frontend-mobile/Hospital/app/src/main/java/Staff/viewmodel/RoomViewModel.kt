package Staff.viewmodel

import Staff.data.RoomDTO
import Staff.data.RoomRepository
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RoomViewModel(private val userRole: String, private val repository: RoomRepository = RoomRepository(userRole)) : ViewModel() {

    sealed class RoomState {
        object Idle : RoomState()
        object Loading : RoomState()
        data class Success(val message: String) : RoomState()
        data class Error(val message: String) : RoomState()
        data class RoomList(val rooms: List<RoomDTO>) : RoomState() // to reflect state changes
    }

    private val _roomState = MutableStateFlow<RoomState>(RoomState.Idle)
    val roomState: StateFlow<RoomState> = _roomState

    // Get a specific room by ID
    fun getRoomById(roomId: Int) {
        viewModelScope.launch {
            _roomState.value = RoomState.Loading
            val result = repository.getRoomById(roomId)
            _roomState.value = if (result.isSuccess) {
                RoomState.RoomList(listOf(result.getOrNull()!!)) // Wrapping in a list to match RoomList type
            } else {
                RoomState.Error(result.exceptionOrNull()?.message ?: "Error fetching room")
            }
        }
    }

    // In RoomViewModel
    fun getAllRooms() {
        viewModelScope.launch {
            _roomState.value = RoomState.Loading
            val result = repository.getAllRooms()  // Assuming you have a getAllRooms method in RoomRepository
            _roomState.value = if (result.isSuccess) {
                RoomState.RoomList(result.getOrNull().orEmpty())
            } else {
                RoomState.Error(result.exceptionOrNull()?.message ?: "Error fetching rooms")
            }
        }
    }

    // Create a room
    fun createRoom(room: RoomDTO) {
        viewModelScope.launch {
            _roomState.value = RoomState.Loading
            val result = repository.createRoom(room)
            _roomState.value = if (result.isSuccess) {
                // If the room is created successfully, add it to the room list in the state
                val updatedRooms = (_roomState.value as? RoomState.RoomList)?.rooms.orEmpty() + room
                RoomState.RoomList(updatedRooms)
            } else {
                RoomState.Error(result.exceptionOrNull()?.message ?: "Error creating room")
            }
        }
    }

    // Update a room
    fun updateRoom(roomId: Int, room: RoomDTO) {
        viewModelScope.launch {
            _roomState.value = RoomState.Loading
            val result = repository.updateRoom(roomId, room)
            _roomState.value = if (result.isSuccess) {
                val updatedRooms = (_roomState.value as? RoomState.RoomList)?.rooms?.map {
                    if (it.roomId == roomId) room else it
                }.orEmpty()
                RoomState.RoomList(updatedRooms)
            } else {
                RoomState.Error(result.exceptionOrNull()?.message ?: "Error updating room")
            }
        }
    }

    // Delete a room
    fun deleteRoom(roomId: Int) {
        viewModelScope.launch {
            _roomState.value = RoomState.Loading
            val result = repository.deleteRoom(roomId)
            _roomState.value = if (result.isSuccess) {
                val updatedRooms = (_roomState.value as? RoomState.RoomList)?.rooms?.filterNot { it.roomId == roomId }.orEmpty()
                RoomState.RoomList(updatedRooms)
            } else {
                RoomState.Error(result.exceptionOrNull()?.message ?: "Error deleting room")
            }
        }
    }
}
