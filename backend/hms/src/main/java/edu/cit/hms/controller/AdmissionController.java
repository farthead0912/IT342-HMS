package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping(value = "/api/admission", produces = "application/json", consumes = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')") // Class-level restriction
public class AdmissionController {
    @Autowired
    private AdmissionService admissionService;

    // Gets all admissions
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved admissions")
    public ResponseEntity<List<AdmissionEntity>> getAdmissions() {
        List<AdmissionEntity> admissions = admissionService.getAdmissions();
        return ResponseEntity.ok(admissions); // Wrap the list in ResponseEntity
    }

    // Gets admission by ID
    @GetMapping(value = "/{admissionId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved admission")
    public ResponseEntity<AdmissionEntity> getAdmissionById(@PathVariable int admissionId) {
        AdmissionEntity admission = admissionService.getAdmissionById(admissionId); // Ensure this returns AdmissionEntity
        return ResponseEntity.ok(admission);
    }

    // Creates new admission
    @PostMapping(value = "/")
    @ApiResponse(responseCode = "201", description = "Successfully created admission")
    public ResponseEntity<AdmissionEntity> createAdmission(@RequestBody AdmissionEntity admission) {
        AdmissionEntity createdAdmission = admissionService.createAdmission(admission); // Ensure this returns AdmissionEntity
        return ResponseEntity.status(201).body(createdAdmission);
    }

    // Updates admission details by ID
    @PutMapping(value = "/{admissionId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated admission")
    public ResponseEntity<AdmissionEntity> updateAdmission(@PathVariable int admissionId, @RequestBody AdmissionEntity admission) {
        AdmissionEntity updatedAdmission = admissionService.updateAdmission(admissionId, admission); // Ensure this returns AdmissionEntity
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
