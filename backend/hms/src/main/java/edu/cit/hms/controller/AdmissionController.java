package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.AdmissionDTO;
import edu.cit.hms.entity.AdmissionEntity;
import edu.cit.hms.service.AdmissionService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Admission not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping(value = "/api/admission", produces = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')") // Class-level restriction
public class AdmissionController {
    @Autowired
    private AdmissionService admissionService;

    // Gets all admissions
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved admissions")
    public ResponseEntity<List<AdmissionDTO>> getAdmissions() {
        List<AdmissionDTO> admissions = admissionService.getAdmissions();
        return ResponseEntity.ok(admissions); // Wrap the list in ResponseEntity
    }

    // Gets admission by ID
    @GetMapping(value = "/{admissionId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved admission")
    public ResponseEntity<AdmissionDTO> getAdmissionById(@PathVariable int admissionId) {
        AdmissionDTO admission = admissionService.getAdmissionById(admissionId); // Ensure this returns AdmissionEntity
        return ResponseEntity.ok(admission);
    }

    @GetMapping(value = "/patient/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved admissions by patient ID")
    public ResponseEntity<List<AdmissionDTO>> getAdmissionsByPatientId(@PathVariable int patientId) {
        List<AdmissionDTO> admissions = admissionService.getAdmissionsByPatientId(patientId); // Ensure this returns List<AdmissionEntity>
        return ResponseEntity.ok(admissions);
    }

    // Creates new admission
    @PostMapping(value = "/", consumes = "application/json")
    @ApiResponse(responseCode = "201", description = "Successfully created admission")
    public ResponseEntity<AdmissionDTO> createAdmission(@RequestBody AdmissionDTO admission) {
        AdmissionDTO createdAdmission = admissionService.createAdmission(admission); // Ensure this returns AdmissionEntity
        return ResponseEntity.status(201).body(createdAdmission);
    }

    // Updates admission details by ID
    @PutMapping(value = "/{admissionId}", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully updated admission")
    public ResponseEntity<AdmissionDTO> updateAdmission(@PathVariable int admissionId, @RequestBody AdmissionDTO admission) {
        AdmissionDTO updatedAdmission = admissionService.updateAdmission(admissionId, admission); // Ensure this returns AdmissionEntity
        return ResponseEntity.ok(updatedAdmission);
    }

    // Deletes admission by ID
    @PreAuthorize("hasRole('ADMIN')") // Method-level override for stricter access
    @DeleteMapping(value = "/{admissionId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted admission")
    public ResponseEntity<String> deleteAdmission(@PathVariable int admissionId) {
        admissionService.deleteAdmission(admissionId); // Ensure this returns void
        return ResponseEntity.ok("Admission deleted successfully");
    }
}