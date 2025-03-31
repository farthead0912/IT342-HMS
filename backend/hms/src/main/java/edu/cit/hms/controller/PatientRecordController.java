package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.PatientRecordEntity;
import edu.cit.hms.service.PatientRecordService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/patient_record")
public class PatientRecordController {
    @Autowired
    private PatientRecordService patientRecordService;

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patient records"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<PatientRecordEntity> getPatientRecords() {
        return patientRecordService.getPatientRecords();
    }

    @GetMapping("/{patientRecordId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved patient record by ID"),
        @ApiResponse(responseCode = "404", description = "Patient record not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PatientRecordEntity getPatientRecordById(@PathVariable int patientRecordId) {
        return patientRecordService.getPatientRecordById(patientRecordId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created patient record"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PatientRecordEntity createPatientRecord(@RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.createPatientRecord(patientRecord);
    }

    @PutMapping("/{patientRecordId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated patient record"),
        @ApiResponse(responseCode = "404", description = "Patient record not found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public PatientRecordEntity updatePatientRecord(@PathVariable int patientRecordId, @RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.updatePatientRecord(patientRecordId, patientRecord);
    }

    @DeleteMapping("/{patientRecordId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted patient record"),
        @ApiResponse(responseCode = "404", description = "Patient record not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deletePatientRecord(@PathVariable int patientRecordId) {
        return patientRecordService.deletePatientRecord(patientRecordId);
    }
}
