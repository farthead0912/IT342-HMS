package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.service.EquipmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of equipment"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<EquipmentEntity> getEquipments() {
        return equipmentService.getEquipments();
    }

    @GetMapping("/{equipmentId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved equipment by ID"),
        @ApiResponse(responseCode = "404", description = "Equipment not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EquipmentEntity getEquipmentById(@PathVariable int equipmentId) {
        return equipmentService.getEquipmentById(equipmentId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created equipment"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EquipmentEntity createEquipment(@RequestBody EquipmentEntity equipment) {
        return equipmentService.createEquipment(equipment);
    }

    @PutMapping("/{equipmentId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated equipment"),
        @ApiResponse(responseCode = "404", description = "Equipment not found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public EquipmentEntity updateEquipment(@PathVariable int equipmentId, @RequestBody EquipmentEntity equipment) {
        return equipmentService.updateEquipment(equipmentId, equipment);
    }

    @DeleteMapping("/{equipmentId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted equipment"),
        @ApiResponse(responseCode = "404", description = "Equipment not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deleteEquipment(@PathVariable int equipmentId) {
        return equipmentService.deleteEquipment(equipmentId);
    }
}
