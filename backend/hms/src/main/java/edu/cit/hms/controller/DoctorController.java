package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.dto.DoctorDTO;
import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.service.DoctorService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Doctor not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping(value = "/api/doctor", produces = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR', 'PATIENT')") // Class-level restriction
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    // Gets all doctors
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of doctors")
    public ResponseEntity<List<DoctorDTO>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getDoctorDTOs());
    }

    // Gets doctor by ID
    @GetMapping(value = "/{doctorId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved doctor by ID")
    public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorService.getDoctorDTOById(doctorId));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create doctors
    @PostMapping(value = "/", consumes = "application/json")
    @ApiResponse(responseCode = "201", description = "Successfully created doctor")
    public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctor) {
        return ResponseEntity.status(201).body(doctorService.createDoctor(doctor));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update doctors
    @PutMapping(value = "/{doctorId}", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully updated doctor")
    public ResponseEntity<DoctorDTO> updateDoctor(@PathVariable int doctorId, @RequestBody DoctorDTO doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId, doctor));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete doctors
    @DeleteMapping(value = "/{doctorId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted doctor")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorService.deleteDoctor(doctorId));
    }
}