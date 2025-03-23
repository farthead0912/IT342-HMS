package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.service.RoomService;

@RestController
@RequestMapping("/api/room")
public class RoomController {
    @Autowired
    private RoomService roomService;

    @GetMapping("/all")
    public List<RoomEntity> getRooms() {
        return roomService.getRooms();
    }

    @GetMapping("/{id}")
    public RoomEntity getRoomById(@PathVariable int id) {
        return roomService.getRoomById(id);
    }

    @PostMapping("/add")
    public RoomEntity createRoom(@RequestBody RoomEntity room) {
        return roomService.createRoom(room);
    }

    @PutMapping("/update/{id}")
    public RoomEntity updateRoom(@PathVariable int roomId, @RequestBody RoomEntity room) {
        return roomService.updateRoom(roomId, room);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteRoom(@PathVariable int id) {
        roomService.deleteRoom(id);
    }
}
