package edu.cit.hms.service;

// import java.util.List;
// import java.util.Optional;

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

    /* public EquipmentEntity getEquipmentById(int equipmentId) {
        return equipmentRepository.findById(equipmentId).orElse(null);
    }

    public List<EquipmentEntity> getAllEquipments() {
        return equipmentRepository.findAll();
    }

    public Optional<EquipmentEntity> getEquipment(int equipmentId) {
        return equipmentRepository.findById(equipmentId);
    } */
}
