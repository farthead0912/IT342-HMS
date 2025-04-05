package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/doctor")
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')") // Class-level restriction
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of doctors")
    public ResponseEntity<List<DoctorEntity>> getAllDoctors() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
    @GetMapping("/{doctorId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved doctor by ID")
    public ResponseEntity<DoctorEntity> getDoctorById(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorService.getDoctorById(doctorId));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create doctors
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created doctor")
    public ResponseEntity<DoctorEntity> createDoctor(@RequestBody DoctorEntity doctor) {
        return ResponseEntity.status(201).body(doctorService.createDoctor(doctor));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update doctors
    @PutMapping("/{doctorId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated doctor")
    public ResponseEntity<DoctorEntity> updateDoctor(@PathVariable int doctorId, @RequestBody DoctorEntity doctor) {
        return ResponseEntity.ok(doctorService.updateDoctor(doctorId, doctor));
    }

    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete doctors
    @DeleteMapping("/{doctorId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted doctor")
    public ResponseEntity<String> deleteDoctor(@PathVariable int doctorId) {
        return ResponseEntity.ok(doctorService.deleteDoctor(doctorId));
    }
}
