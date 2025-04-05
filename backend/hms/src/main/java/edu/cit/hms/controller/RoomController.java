package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created room")
    public ResponseEntity<RoomEntity> createRoom(@RequestBody RoomEntity room) {
        return ResponseEntity.ok(roomService.createRoom(room));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated room")
    public ResponseEntity<RoomEntity> updateRoom(@PathVariable int roomId, @RequestBody RoomEntity room) {
        return ResponseEntity.ok(roomService.updateRoom(roomId, room));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{roomId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted room")
    public ResponseEntity<String> deleteRoom(@PathVariable int roomId) {
        return ResponseEntity.ok(roomService.deleteRoom(roomId));
    }
}
