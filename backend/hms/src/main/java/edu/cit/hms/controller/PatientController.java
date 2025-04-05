package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.http.ResponseEntity;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.service.PatientService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Patient not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping("/api/patient")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'STAFF')")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'STAFF')")
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patients")
    public ResponseEntity<List<PatientEntity>> getPatients() {
        return ResponseEntity.ok(patientService.getPatients());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'STAFF')")
    @GetMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved patient by ID")
    public ResponseEntity<PatientEntity> getPatientById(@PathVariable int patientId) {
        return ResponseEntity.ok(patientService.getPatientById(patientId));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created patient")
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient) {
        return ResponseEntity.status(201).body(patientService.createPatient(patient));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated patient")
    public ResponseEntity<PatientEntity> updatePatient(@PathVariable int patientId, @RequestBody PatientEntity patient) {
        return ResponseEntity.ok(patientService.updatePatient(patientId, patient));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted patient")
    public ResponseEntity<String> deletePatient(@PathVariable int patientId) {
        return ResponseEntity.ok(patientService.deletePatient(patientId));
    }
}
