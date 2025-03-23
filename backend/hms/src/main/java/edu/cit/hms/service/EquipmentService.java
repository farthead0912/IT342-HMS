package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.repository.EquipmentRepository;

@Service
public class EquipmentService {
    @Autowired
    private EquipmentRepository equipmentRepository;

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
        /* if (newEquipment.getDepartments() != null) {
            equipment.setDepartments(newEquipment.getDepartments());
        } */

        return equipmentRepository.save(equipment);
    }

    public void deleteEquipment(int equipmentId) {
        Optional<EquipmentEntity> equipment = equipmentRepository.findById(equipmentId);

        if (equipment.isPresent()) {
            equipmentRepository.deleteById(equipmentId);
        } else {
            throw new RuntimeException("Equipment ID: " + equipmentId + " not found!");
        }
    }
}
