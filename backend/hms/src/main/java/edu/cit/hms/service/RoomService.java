package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    public RoomEntity createRoom(RoomEntity roomEntity) {
        return roomRepository.save(roomEntity);
    }

    public RoomEntity getRoomById(int roomId) {
        return roomRepository.findById(roomId).orElse(null);
    }

    public List<RoomEntity> getRooms() {
        return roomRepository.findAll();
    }

    public RoomEntity updateRoom(int roomId, RoomEntity newRoom) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room ID: " + roomId + " not found!"));

        // Validate new data
        if(newRoom.getRoomNumber() != 0) {
            room.setRoomNumber(newRoom.getRoomNumber());
        }
        if(newRoom.getRoomType() != null && !newRoom.getRoomType().isEmpty()) {
            room.setRoomType(newRoom.getRoomType());
        }
        if(newRoom.isOccupied() != room.isOccupied()) {
            room.setOccupied(newRoom.isOccupied());
        }
        if(newRoom.getRoomPrice() > 0) {
            room.setRoomPrice(newRoom.getRoomPrice());
        }
        if(newRoom.getPatient() != null) {
            room.setPatient(newRoom.getPatient());
        }

        return roomRepository.save(room);
    }
    
    public void deleteRoom(int roomId) {
        Optional<RoomEntity> room = roomRepository.findById(roomId);

        if(room.isPresent()) {
            roomRepository.deleteById(roomId);
        } else {
            throw new RuntimeException("Room ID: " + roomId + " not found!");
        }
    }
}
