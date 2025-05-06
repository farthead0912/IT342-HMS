package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.DepartmentEquipmentDTO;
import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.junctions.DepartmentEquipment;
import edu.cit.hms.repository.DepartmentEquipmentRepository;
import edu.cit.hms.repository.DepartmentRepository;
import edu.cit.hms.repository.EquipmentRepository;

@Service
public class DepartmentEquipmentService {
    @Autowired
    private DepartmentEquipmentRepository departmentEquipmentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private EquipmentRepository equipmentRepository;

    public DepartmentEquipment createDepartmentEquipment(DepartmentEquipment departmentEquipment) {
        return departmentEquipmentRepository.save(departmentEquipment);
    }

    public DepartmentEquipment getDepartmentEquipmentById(int deeqId) {
        return departmentEquipmentRepository.findById(deeqId).orElse(null);
    }

    public List<DepartmentEquipment> getAllDepartmentEquipments() {
        return departmentEquipmentRepository.findAll();
    }

    public DepartmentEquipment updateDepartmentEquipment(int deeqId, DepartmentEquipment newDepartmentEquipment) {
        DepartmentEquipment departmentEquipment = departmentEquipmentRepository.findById(deeqId)
                .orElseThrow(() -> new RuntimeException("DepartmentEquipment ID: " + deeqId + " not found!"));

        if (newDepartmentEquipment.getEquipment() != null) {
            departmentEquipment.setEquipment(newDepartmentEquipment.getEquipment());
        }
        if (newDepartmentEquipment.getDepartment() != null) {
            departmentEquipment.setDepartment(newDepartmentEquipment.getDepartment());
        }

        return departmentEquipmentRepository.save(departmentEquipment);
    }

    public String deleteDepartmentEquipment(int deeqId) {
        Optional<DepartmentEquipment> departmentEquipment = departmentEquipmentRepository.findById(deeqId);

        if (departmentEquipment.isPresent()) {
            departmentEquipmentRepository.deleteById(deeqId);
            return "DepartmentEquipment ID: " + deeqId + " deleted successfully!";
        } else {
            throw new RuntimeException("DepartmentEquipment ID: " + deeqId + " not found!");
        }
    }

    private DepartmentEquipment convertFromDTO(DepartmentEquipmentDTO dto) {
        DepartmentEquipment departmentEquipment = new DepartmentEquipment();

        EquipmentEntity equipment = equipmentRepository.findById(dto.getEquipmentId())
                .orElseThrow(() -> new RuntimeException("Equipment ID: " + dto.getEquipmentId() + " not found!"));
        DepartmentEntity department = departmentRepository.findById(dto.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department ID: " + dto.getDepartmentId() + " not found!"));

        departmentEquipment.setEquipment(equipment);
        departmentEquipment.setDepartment(department);

        return departmentEquipment;
    }

    private DepartmentEquipmentDTO convertToDTO(DepartmentEquipment departmentEquipment) {
        DepartmentEquipmentDTO dto = new DepartmentEquipmentDTO();

        dto.setDeeqId(departmentEquipment.getdeeqId());
        dto.setEquipmentId(departmentEquipment.getEquipment().getEquipmentId());
        dto.setDepartmentId(departmentEquipment.getDepartment().getDeptId());

        return dto;
    }
}
