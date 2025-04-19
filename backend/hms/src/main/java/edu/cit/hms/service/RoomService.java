package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
        RoomEntity room = convertFromDTO(roomDTO);

        return roomRepository.save(room);
    }

    public RoomEntity getRoomById(int roomId) {
        return roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room ID: " + roomId + " not found!"));
    }

    public List<RoomDTO> getRooms() {
        try {
            List<RoomEntity> rooms = roomRepository.findAll();
            return rooms.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving rooms: " + e.getMessage());
            throw new RuntimeException("Error retrieving rooms at this moment. Please try again later.");
        }
    }

    public RoomEntity updateRoom(int roomId, RoomDTO newRoomDTO) {
        RoomEntity room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RuntimeException("Room ID: " + roomId + " not found!"));

        // Validate and update fields
        if (newRoomDTO.getRoomNumber() != 0) {
            room.setRoomNumber(newRoomDTO.getRoomNumber());
        }
        if (newRoomDTO.getRoomType() != null && !newRoomDTO.getRoomType().isEmpty()) {
            room.setRoomType(newRoomDTO.getRoomType());
        }
        if (newRoomDTO.isOccupied() != room.isOccupied()) {
            room.setOccupied(newRoomDTO.isOccupied());
        }
        if (newRoomDTO.getRoomPrice() > 0) {
            room.setRoomPrice(newRoomDTO.getRoomPrice());
        }
        if (newRoomDTO.getPatientId() != 0) {
            room.setPatient(patientRepository.findById(newRoomDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + newRoomDTO.getPatientId() + " not found!")));
        }
        if (newRoomDTO.getStaffId() != 0) {
            room.setStaff(staffRepository.findById(newRoomDTO.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff ID: " + newRoomDTO.getStaffId() + " not found!")));
        }

        return roomRepository.save(room);
    }

    public String deleteRoom(int roomId) {
        Optional<RoomEntity> room = roomRepository.findById(roomId);

        if (room.isPresent()) {
            roomRepository.deleteById(roomId);
            return "Room ID: " + roomId + " deleted successfully!";
        } else {
            throw new RuntimeException("Room ID: " + roomId + " not found!");
        }
    }

    private RoomDTO convertToDTO(RoomEntity room) {
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(room.getRoomId());
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setRoomType(room.getRoomType());
        roomDTO.setOccupied(room.isOccupied());
        roomDTO.setRoomPrice(room.getRoomPrice());
        roomDTO.setPatientId(room.getPatient().getPatientId());
        roomDTO.setFloorNumber(room.getFloorNumber());
        roomDTO.setStaffId(room.getStaff().getStaffId());

        if (room.getAdmissions() != null) {
            List<Integer> admissionIds = room.getAdmissions().stream()
                    .map(AdmissionEntity::getAdmissionId)
                    .toList();
            roomDTO.setAdmissionIdList(admissionIds);
        }

        return roomDTO;
    }

    private RoomEntity convertFromDTO(RoomDTO roomDTO) {
        RoomEntity room = new RoomEntity();

        room.setRoomNumber(roomDTO.getRoomNumber());
        room.setRoomType(roomDTO.getRoomType());
        room.setOccupied(roomDTO.isOccupied());
        room.setRoomPrice(roomDTO.getRoomPrice());
        room.setFloorNumber(roomDTO.getFloorNumber());

        if (roomDTO.getPatientId() != 0) {
            PatientEntity patient = patientRepository.findById(roomDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + roomDTO.getPatientId() + " not found!"));
            room.setPatient(patient);
        }

        if (roomDTO.getStaffId() != 0) {
            StaffEntity staff = staffRepository.findById(roomDTO.getStaffId())
                    .orElseThrow(() -> new RuntimeException("Staff ID: " + roomDTO.getStaffId() + " not found!"));
            room.setStaff(staff);
        }

        if (roomDTO.getAdmissionIdList() != null && !roomDTO.getAdmissionIdList().isEmpty()) {
            List<AdmissionEntity> admissions = roomDTO.getAdmissionIdList().stream()
                    .map(admissionId -> admissionRepository.findById(admissionId)
                            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!")))
                    .toList();
            room.setAdmissions(admissions);
        }

        return room;
    }
}
