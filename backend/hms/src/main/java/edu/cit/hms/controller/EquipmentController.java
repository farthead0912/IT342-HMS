package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.service.EquipmentService;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/all")
    public List<EquipmentEntity> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{id}")
    public EquipmentEntity getEquipmentById(@PathVariable int equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    @PostMapping("/add")
    public EquipmentEntity createEquipment(@RequestBody EquipmentEntity equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @PutMapping("/update/{id}")
    public EquipmentEntity updateEquipment(@PathVariable int equipmentId, @RequestBody EquipmentEntity equipment) {
        return equipmentService.updateEquipment(equipmentId, equipment);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEquipment(@PathVariable int id) {
        return equipmentService.deleteEquipment(id);
    }
}
