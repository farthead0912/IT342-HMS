package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.AdmissionEntity;
import edu.cit.hms.service.AdmissionService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/admission")
public class AdmissionController {
    @Autowired
    private AdmissionService admissionService;

    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved admissions"),
            @ApiResponse(responseCode = "404", description = "Admission not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<List<AdmissionEntity>> getAdmissions() {
        return admissionService.getAdmissions();
    }

    @GetMapping("/{admissionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved admission"),
            @ApiResponse(responseCode = "404", description = "Admission not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AdmissionEntity> getAdmissionById(@PathVariable int admissionId) {
        return admissionService.getAdmissionById(admissionId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created admission"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AdmissionEntity> createAdmission(@RequestBody AdmissionEntity admission) {
        return admissionService.createAdmission(admission);
    }

    @PutMapping("/{id}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated admission"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Admission not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<AdmissionEntity> updateAdmission(@PathVariable int admissionId, @RequestBody AdmissionEntity admission) {
        return admissionService.updateAdmission(admissionId, admission);
    }

    @DeleteMapping("/{admissionId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted admission"),
            @ApiResponse(responseCode = "404", description = "Admission not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public ResponseEntity<String> deleteAdmission(@PathVariable int admissionId) {
        return admissionService.deleteAdmission(admissionId);
    }
}
