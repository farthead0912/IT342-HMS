package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.PatientRecordDTO;
import edu.cit.hms.entity.PatientRecordEntity;
import edu.cit.hms.service.PatientRecordService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Patient record not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping(value = "/api/patient_record", produces = "application/json", consumes = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'STAFF')") // Class-level restriction
public class PatientRecordController {
    @Autowired
    private PatientRecordService patientRecordService;

    // Gets all patient records
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patient records")
    public ResponseEntity<List<PatientRecordEntity>> getPatientRecords() {
        return ResponseEntity.ok(patientRecordService.getPatientRecords());
    }

    // Gets patient record by ID
    @GetMapping(value = "/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved patient record by ID")
    public ResponseEntity<PatientRecordEntity> getPatientRecordById(@PathVariable int patientRecordId) {
        return ResponseEntity.ok(patientRecordService.getPatientRecordById(patientRecordId));
    }

    // Creates a new patient record
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')") // ADMINs and DOCTORs can create patient records
    @PostMapping(value = "/")
    @ApiResponse(responseCode = "201", description = "Successfully created patient record")
    public ResponseEntity<PatientRecordEntity> createPatientRecord(@RequestBody PatientRecordDTO patientRecord) {
        return ResponseEntity.status(201).body(patientRecordService.createPatientRecord(patientRecord));
    }

    // Updates patient record details by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')") // ADMINs and DOCTORs can update patient records
    @PutMapping(value = "/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated patient record")
    public ResponseEntity<PatientRecordEntity> updatePatientRecord(@PathVariable int patientRecordId, @RequestBody PatientRecordEntity patientRecord) {
        return ResponseEntity.ok(patientRecordService.updatePatientRecord(patientRecordId, patientRecord));
    }

    // Deletes patient record by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete patient records
    @DeleteMapping(value = "/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted patient record")
    public ResponseEntity<String> deletePatientRecord(@PathVariable int patientRecordId) {
        return ResponseEntity.ok(patientRecordService.deletePatientRecord(patientRecordId));
    }
}
