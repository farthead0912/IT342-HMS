package edu.cit.hms.controller;

import edu.cit.hms.dto.PrescriptionDTO;
import edu.cit.hms.service.PrescriptionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/prescriptions")
@CrossOrigin
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request was successful"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @PostMapping(consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Prescription created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<PrescriptionDTO> createPrescription(@RequestBody PrescriptionDTO prescriptionDTO) {
        PrescriptionDTO createdPrescription = prescriptionService.createPrescription(prescriptionDTO);
        return ResponseEntity.ok(createdPrescription);
    }

    @GetMapping(value = "/{prescriptionId}", produces = "application/json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched prescription successfully"),
        @ApiResponse(responseCode = "404", description = "Prescription not found")
    })
    public ResponseEntity<PrescriptionDTO> getPrescriptionById(@PathVariable int prescriptionId) {
        PrescriptionDTO prescription = prescriptionService.getPrescriptionById(prescriptionId);
        return ResponseEntity.ok(prescription);
    }

    @GetMapping(value = "/patient/{patientId}", produces = "application/json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched prescriptions for the patient successfully"),
        @ApiResponse(responseCode = "404", description = "No prescriptions found for the given patient ID")
    })
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByPatientId(@PathVariable int patientId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getPrescriptionByPatientId(patientId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping(value = "/doctor/{doctorId}", produces = "application/json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched prescriptions for the doctor successfully"),
        @ApiResponse(responseCode = "404", description = "No prescriptions found for the given doctor ID")
    })
    public ResponseEntity<List<PrescriptionDTO>> getPrescriptionsByDoctorId(@PathVariable int doctorId) {
        List<PrescriptionDTO> prescriptions = prescriptionService.getPrescriptionsByDoctorId(doctorId);
        return ResponseEntity.ok(prescriptions);
    }

    @GetMapping(produces = "application/json")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched all prescriptions successfully"),
        @ApiResponse(responseCode = "404", description = "No prescriptions found")
    })
    public ResponseEntity<List<PrescriptionDTO>> getAllPrescriptions() {
        List<PrescriptionDTO> prescriptions = prescriptionService.getAllPrescriptions();
        return ResponseEntity.ok(prescriptions);
    }

    @PutMapping(value = "/{prescriptionId}", consumes = "application/json", produces = "application/json")
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prescription updated successfully"),
        @ApiResponse(responseCode = "404", description = "Prescription not found")
    })
    public ResponseEntity<PrescriptionDTO> updatePrescription(@PathVariable int prescriptionId, @RequestBody PrescriptionDTO prescriptionDTO) {
        prescriptionDTO.setPrescriptionId(prescriptionId);
        PrescriptionDTO updatedPrescription = prescriptionService.updatePrescription(prescriptionDTO);
        return ResponseEntity.ok(updatedPrescription);
    }

    @DeleteMapping(value = "/{prescriptionId}", produces = "application/json")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prescription deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Prescription not found")
    })
    public ResponseEntity<String> deletePrescription(@PathVariable int prescriptionId) {
        String response = prescriptionService.deletePrescription(prescriptionId);
        return ResponseEntity.ok(response);
    }
}
