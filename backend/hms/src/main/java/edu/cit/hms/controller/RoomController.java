package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.RoomDTO;
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
@RequestMapping(value = "/api/room", produces = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF', 'PATIENT')")
public class RoomController {
    @Autowired
    private RoomService roomService;

    // Gets all rooms
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of rooms")
    public List<RoomDTO> getRooms() {
        return roomService.getRooms();
    }

    // Gets room by ID
    @GetMapping(value = "/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved room by ID")
    public RoomEntity getRoomById(@PathVariable int roomId) {
        return roomService.getRoomById(roomId);
    }

    // Creates a new room
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping(value = "/", consumes = "application/json")
    @ApiResponse(responseCode = "201", description = "Successfully created room")
    public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomDTO room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    // Updates room details by ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping(value = "/{roomId}", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully updated room")
    public ResponseEntity<RoomEntity> updateRoom(@PathVariable int roomId, @RequestBody RoomDTO room) {
        return ResponseEntity.ok(roomService.updateRoom(roomId, room));
    }

    // Deletes room by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping(value = "/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted room")
    public ResponseEntity<String> deleteRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(roomService.deleteRoom(roomId));
    }
}
