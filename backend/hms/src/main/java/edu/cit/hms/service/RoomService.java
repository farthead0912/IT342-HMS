package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.RoomDTO;
import edu.cit.hms.entity.*;
import edu.cit.hms.repository.*;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private AdmissionRepository admissionRepository;

    public RoomEntity createRoom(RoomDTO roomDTO) {
        RoomEntity room = new RoomEntity();
        PatientEntity patient = patientRepository.findById(roomDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient ID: " + roomDTO.getPatientId() + " not found!"));
        StaffEntity staff = staffRepository.findById(roomDTO.getStaffId())
                .orElseThrow(() -> new RuntimeException("Staff ID: " + roomDTO.getStaffId() + " not found!"));
        // List<AdmissionEntity> admissions = admissionRepository.findBy

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setOccupied(roomDTO.isOccupied()); // Default value
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setPatient(patient);
        room.setFloorNumber(roomDTO.getFloorNumber());
        room.setStaff(staff);
        // room.getAdmissions();

        return roomRepository.save(room);
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
    
    public String deleteRoom(int roomId) {
        Optional<RoomEntity> room = roomRepository.findById(roomId);

        if(room.isPresent()) {
            roomRepository.deleteById(roomId);

            return "Room ID: " + roomId + " deleted successfully!";
        } else {
            throw new RuntimeException("Room ID: " + roomId + " not found!");
        }
    }
}
