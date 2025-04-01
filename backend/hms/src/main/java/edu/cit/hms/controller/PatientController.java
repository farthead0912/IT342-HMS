package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patients")
    public List<PatientEntity> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved patient by ID")
    public PatientEntity getPatientById(@PathVariable int patientId) {
        return patientService.getPatientById(patientId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created patient")
    public PatientEntity createPatient(@RequestBody PatientEntity patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated patient")
    public PatientEntity updatePatient(@PathVariable int patientId, @RequestBody PatientEntity patient) {
        return patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping("/{patientId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted patient")
    public String deletePatient(@PathVariable int patientId) {
        return patientService.deletePatient(patientId);
    }
}
