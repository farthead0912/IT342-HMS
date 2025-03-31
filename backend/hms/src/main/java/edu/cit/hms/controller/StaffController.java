package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.service.StaffService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved list of staff"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<StaffEntity> getStaff() {
        return staffService.getStaff();
    }

    @GetMapping("/{staffId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully retrieved staff by ID"),
        @ApiResponse(responseCode = "404", description = "Staff not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public StaffEntity getStaffById(@PathVariable int staffId) {
        return staffService.getStaffById(staffId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Successfully created staff"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public StaffEntity createStaff(@RequestBody StaffEntity staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("/{staffId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully updated staff"),
        @ApiResponse(responseCode = "404", description = "Staff not found"),
        @ApiResponse(responseCode = "400", description = "Bad request"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public StaffEntity updateStaff(@PathVariable int staffId, @RequestBody StaffEntity staff) {
        return staffService.updateStaff(staffId, staff);
    }

    @DeleteMapping("/{staffId}")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successfully deleted staff"),
        @ApiResponse(responseCode = "404", description = "Staff not found"),
        @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deleteStaff(@PathVariable int staffId) {
        return staffService.deleteStaff(staffId);
    }
}
