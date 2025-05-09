package Staff.network

import Staff.data.RoomDTO
import Staff.data.RoomResponse
import retrofit2.Response
import retrofit2.http.*

interface RoomService {

    @GET("api/room/")  // Adjusted endpoint
    suspend fun getAllRooms(): Response<List<RoomDTO>>

    @GET("api/room/{roomId}")  // Adjusted endpoint
    suspend fun getRoom(@Path("roomId") roomId: Int): Response<RoomDTO>

    @POST("api/room/")  // Adjusted endpoint
    suspend fun createRoom(@Body roomDTO: RoomDTO): Response<RoomDTO>

    @PUT("api/room/{roomId}")  // Adjusted endpoint
    suspend fun updateRoom(@Path("roomId") roomId: Int, @Body roomDTO: RoomDTO): Response<RoomDTO>

    @DELETE("api/room/{roomId}")  // Adjusted endpoint
    suspend fun deleteRoom(@Path("roomId") roomId: Int): Response<RoomResponse>

}
