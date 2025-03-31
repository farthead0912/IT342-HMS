package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.service.DoctorService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of doctors"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<DoctorEntity> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{doctorId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved doctor by ID"),
        @ApiResponse(responseCode = "404", description = "Doctor not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DoctorEntity getDoctorById(@PathVariable int doctorId) {
        return doctorService.getDoctorById(doctorId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created doctor"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DoctorEntity createDoctor(@RequestBody DoctorEntity doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/{doctorId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated doctor"),
        @ApiResponse(responseCode = "404", description = "Doctor not found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DoctorEntity updateDoctor(@PathVariable int doctorId, @RequestBody DoctorEntity doctor) {
        return doctorService.updateDoctor(doctorId, doctor);
    }

    @DeleteMapping("/{doctorId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted doctor"),
        @ApiResponse(responseCode = "404", description = "Doctor not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deleteDoctor(@PathVariable int doctorId) {
        return doctorService.deleteDoctor(doctorId);
    }
}
