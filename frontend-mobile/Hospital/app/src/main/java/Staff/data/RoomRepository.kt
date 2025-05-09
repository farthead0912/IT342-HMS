package Staff.data

import com.example.hospital.network.RetrofitClient
import Staff.network.RoomService

class RoomRepository(private val userRole: String) {
    private val roomService = RetrofitClient.roomService


    suspend fun getRoomById(roomId: Int): Result<RoomDTO> {
        return try {
            val response = roomService.getRoom(roomId)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to fetch room: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // Corrected getAllRooms function
    suspend fun getAllRooms(): Result<List<RoomDTO>> {
        return try {
            val response = roomService.getAllRooms()
            if (response.isSuccessful) {
                // If successful, return the list of rooms
                Result.success(response.body().orEmpty()) // Return an empty list if body is null
            } else {
                // If the response is not successful, return a failure
                Result.failure(Exception(": ${response.message()}"))
            }
        } catch (e: Exception) {
            // Handle any exceptions and return a failure
            Result.failure(e)
        }
    }

    suspend fun createRoom(room: RoomDTO): Result<RoomDTO> {
        return try {
            val response = roomService.createRoom(room)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to create room"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateRoom(roomId: Int, room: RoomDTO): Result<RoomDTO> {
        return try {
            val response = roomService.updateRoom(roomId, room)
            if (response.isSuccessful) {
                Result.success(response.body()!!)
            } else {
                Result.failure(Exception("Failed to update room"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun deleteRoom(roomId: Int): Result<String> {
        return try {
            val response = roomService.deleteRoom(roomId)
            if (response.isSuccessful) {
                Result.success(response.body()?.message ?: "Room deleted")
            } else {
                Result.failure(Exception("Failed to delete room"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
