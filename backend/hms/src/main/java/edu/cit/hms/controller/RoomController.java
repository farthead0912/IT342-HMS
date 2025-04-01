package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.service.RoomService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Room not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms")
    public List<RoomEntity> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved room by ID")
    public RoomEntity getRoomById(@PathVariable int roomId) {
        return roomService.getRoomById(roomId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created room")
    public RoomEntity createRoom(@RequestBody RoomEntity room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated room")
    public RoomEntity updateRoom(@PathVariable int roomId, @RequestBody RoomEntity room) {
        return roomService.updateRoom(roomId, room);
    }

    @DeleteMapping("/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted room")
    public String deleteRoom(@PathVariable int roomId) {
        return roomService.deleteRoom(roomId);
    }
}
