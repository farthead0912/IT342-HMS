package edu.cit.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.junctions.PatientEquipment;
import edu.cit.hms.service.PatientEquipmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "PatientEquipment not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')") // Class-level restriction
@RestController
@RequestMapping(value = "/api/patient-equipment", consumes = "application/json")
public class PatientEquipmentController {

    @Autowired
    private PatientEquipmentService patientEquipmentService;

    @PostMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create
    @ApiResponse(responseCode = "201", description = "Successfully created PatientEquipment")
    public ResponseEntity<PatientEquipment> createPatientEquipment(@RequestBody PatientEquipment patientEquipment) {
        return ResponseEntity.status(201).body(patientEquipmentService.createPatientEquipment(patientEquipment));
    }

    @GetMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved PatientEquipment")
    public ResponseEntity<PatientEquipment> getPatientEquipmentById(@PathVariable int id) {
        return ResponseEntity.ok(patientEquipmentService.getPatientEquipmentById(id));
    }

    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all PatientEquipments")
    public ResponseEntity<List<PatientEquipment>> getAllPatientEquipments() {
        return ResponseEntity.ok(patientEquipmentService.getAllPatientEquipments());
    }

    @PutMapping(value = "/{id}", consumes = "application/json")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update
    @ApiResponse(responseCode = "200", description = "Successfully updated PatientEquipment")
    public ResponseEntity<PatientEquipment> updatePatientEquipment(@PathVariable int id, @RequestBody PatientEquipment patientEquipment) {
        return ResponseEntity.ok(patientEquipmentService.updatePatientEquipment(id, patientEquipment));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete
    @ApiResponse(responseCode = "200", description = "Successfully deleted PatientEquipment")
    public ResponseEntity<String> deletePatientEquipment(@PathVariable int id) {
        return ResponseEntity.ok(patientEquipmentService.deletePatientEquipment(id));
    }
}
