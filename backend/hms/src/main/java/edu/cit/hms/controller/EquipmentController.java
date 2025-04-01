package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.service.EquipmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Equipment not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of equipment")
    public List<EquipmentEntity> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{equipmentId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved equipment by ID")
    public EquipmentEntity getEquipmentById(@PathVariable int equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created equipment")
    public EquipmentEntity createEquipment(@RequestBody EquipmentEntity equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @PutMapping("/{equipmentId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated equipment")
    public EquipmentEntity updateEquipment(@PathVariable int equipmentId, @RequestBody EquipmentEntity equipment) {
        return equipmentService.updateEquipment(equipmentId, equipment);
    }

    @DeleteMapping("/{equipmentId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted equipment")
    public String deleteEquipment(@PathVariable int equipmentId) {
        return equipmentService.deleteEquipment(equipmentId);
    }
}
