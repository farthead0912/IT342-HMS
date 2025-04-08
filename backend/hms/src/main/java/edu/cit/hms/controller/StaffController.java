package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.service.StaffService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "404", description = "Staff not found"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')")
@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    // Gets all staff
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of staff")
    public ResponseEntity<List<StaffEntity>> getStaff() {
        return ResponseEntity.ok(staffService.getStaff());
    }

    // Gets staff by ID
    @GetMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved staff by ID")
    public ResponseEntity<StaffEntity> getStaffById(@PathVariable int staffId) {
        return ResponseEntity.ok(staffService.getStaffById(staffId));
    }

    // Creates a new staff
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created staff")
    public ResponseEntity<StaffEntity> createStaff(@RequestBody StaffEntity staff) {
        return ResponseEntity.status(201).body(staffService.createStaff(staff));
    }

    // Updates staff details by ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated staff")
    public ResponseEntity<StaffEntity> updateStaff(@PathVariable int staffId, @RequestBody StaffEntity staff) {
        return ResponseEntity.ok(staffService.updateStaff(staffId, staff));
    }


    // Deletes staff by ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted staff")
    public ResponseEntity<String> deleteStaff(@PathVariable int staffId) {
        return ResponseEntity.ok(staffService.deleteStaff(staffId));
    }
}
