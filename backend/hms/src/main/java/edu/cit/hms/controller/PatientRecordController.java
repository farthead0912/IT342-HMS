package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/patient_record")
public class PatientRecordController {
    @Autowired
    private PatientRecordService patientRecordService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patient records")
    public List<PatientRecordEntity> getPatientRecords() {
        return patientRecordService.getPatientRecords();
    }

    @GetMapping("/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved patient record by ID")
    public PatientRecordEntity getPatientRecordById(@PathVariable int patientRecordId) {
        return patientRecordService.getPatientRecordById(patientRecordId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created patient record")
    public PatientRecordEntity createPatientRecord(@RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.createPatientRecord(patientRecord);
    }

    @PutMapping("/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated patient record")
    public PatientRecordEntity updatePatientRecord(@PathVariable int patientRecordId, @RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.updatePatientRecord(patientRecordId, patientRecord);
    }

    @DeleteMapping("/{patientRecordId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted patient record")
    public String deletePatientRecord(@PathVariable int patientRecordId) {
        return patientRecordService.deletePatientRecord(patientRecordId);
    }
}
