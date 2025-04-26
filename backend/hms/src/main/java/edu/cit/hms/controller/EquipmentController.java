package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping(value = "/api/equipment", produces = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')") // Class-level restriction
public class EquipmentController {
    @Autowired
    private EquipmentService equipmentService;

    // Gets all equipment
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of equipment")
    public ResponseEntity<List<EquipmentEntity>> getEquipments() {
        return ResponseEntity.ok(equipmentService.getEquipments());
    }

    // Gets equipment by ID
    @GetMapping(value = "/{equipmentId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved equipment by ID")
    public ResponseEntity<EquipmentEntity> getEquipmentById(@PathVariable int equipmentId) {
        return ResponseEntity.ok(equipmentService.getEquipmentById(equipmentId));
    }

    // Creates a new equipment
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create equipment
    @PostMapping(value = "/")
    @ApiResponse(responseCode = "201", description = "Successfully created equipment")
    public ResponseEntity<EquipmentEntity> createEquipment(@RequestBody EquipmentEntity equipment) {
        return ResponseEntity.status(201).body(equipmentService.createEquipment(equipment));
    }

    // Updates equipment details by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update equipment
    @PutMapping(value = "/{equipmentId}", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully updated equipment")
    public ResponseEntity<EquipmentEntity> updateEquipment(@PathVariable int equipmentId, @RequestBody EquipmentEntity equipment) {
        return ResponseEntity.ok(equipmentService.updateEquipment(equipmentId, equipment));
    }

    // Deletes equipment by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete equipment
    @DeleteMapping(value = "/{equipmentId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted equipment")
    public ResponseEntity<String> deleteEquipment(@PathVariable int equipmentId) {
        return ResponseEntity.ok(equipmentService.deleteEquipment(equipmentId));
    }
}
