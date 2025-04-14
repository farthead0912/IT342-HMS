package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.EquipmentDTO;
import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.repository.EquipmentRepository;
import edu.cit.hms.repository.RoomRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

    @Autowired
    private RoomRepository roomRepository;

    public EquipmentEntity createEquipment(EquipmentEntity equipment) {
        return equipmentRepository.save(equipment);
    }

    public EquipmentEntity getEquipmentById(int equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    public List<EquipmentEntity> getEquipments() {
        return equipmentRepository.findAll();
    }

    public EquipmentEntity updateEquipment(int equipmentId, EquipmentEntity newEquipment) {
        EquipmentEntity equipment = equipmentRepository.findById(equipmentId)
                .orElseThrow(() -> new RuntimeException("Equipment ID: " + equipmentId + " not found!"));

        if (newEquipment.getEquipmentName() != null && !newEquipment.getEquipmentName().isEmpty()) {
            equipment.setEquipmentName(newEquipment.getEquipmentName());
        }
        if (newEquipment.getEquipmentType() != null && !newEquipment.getEquipmentType().isEmpty()) {
            equipment.setEquipmentType(newEquipment.getEquipmentType());
        }
        if (newEquipment.getStock() > 0) {
            equipment.setStock(newEquipment.getStock());
        }
        if (newEquipment.getPrice() > 0) {
            equipment.setPrice(newEquipment.getPrice());
        }
        if (newEquipment.getStatus() != null && !newEquipment.getStatus().isEmpty()) {
            equipment.setStatus(newEquipment.getStatus());
        }
        if (newEquipment.getRoom() != null) {
            equipment.setRoom(newEquipment.getRoom());
        }
        if (newEquipment.getDepartments() != null) {
            equipment.setDepartments(newEquipment.getDepartments());
        }

        return equipmentRepository.save(equipment);
    }

    public String deleteEquipment(int equipmentId) {
        Optional<EquipmentEntity> equipment = equipmentRepository.findById(equipmentId);

        if (equipment.isPresent()) {
            equipmentRepository.deleteById(equipmentId);

            return "Equipment ID: " + equipmentId + " deleted successfully!";
        } else {
            throw new RuntimeException("Equipment ID: " + equipmentId + " not found!");
        }
    }

    private EquipmentEntity convertFromDTO(EquipmentEntity equipmentDTO) {
        EquipmentEntity equipment = new EquipmentEntity();

        equipment.setEquipmentName(equipmentDTO.getEquipmentName());
        equipment.setEquipmentType(equipmentDTO.getEquipmentType());
        equipment.setStock(equipmentDTO.getStock());
        equipment.setPrice(equipmentDTO.getPrice());
        equipment.setStatus(equipmentDTO.getStatus());
        equipment.setRoom(equipmentDTO.getRoom());
        equipment.setDepartments(equipmentDTO.getDepartments());

        return equipment;
    }

    private EquipmentDTO convertToDTO(EquipmentEntity equipment) {
        EquipmentDTO equipmentDTO = new EquipmentDTO();
        RoomEntity room = roomRepository.findById(equipment.getRoom().getRoomId())
                .orElseThrow(() -> new RuntimeException("Room ID: " + equipment.getRoom() + " not found!"));
        // Set<DepartmentEntity> departments = 

        equipmentDTO.setEquipmentName(equipment.getEquipmentName());
        equipmentDTO.setEquipmentType(equipment.getEquipmentType());
        equipmentDTO.setStock(equipment.getStock());
        equipmentDTO.setPrice(equipment.getPrice());
        equipmentDTO.setStatus(equipment.getStatus());
        equipmentDTO.setRoomId(room.getRoomId());
        // equipmentDTO.setDepartments(equipment.getDepartments());

        return equipmentDTO;
    }
}
