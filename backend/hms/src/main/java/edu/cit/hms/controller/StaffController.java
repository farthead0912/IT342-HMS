package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of staff")
    public List<StaffEntity> getStaff() {
        return staffService.getStaff();
    }

    @GetMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved staff by ID")
    public StaffEntity getStaffById(@PathVariable int staffId) {
        return staffService.getStaffById(staffId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created staff")
    public StaffEntity createStaff(@RequestBody StaffEntity staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated staff")
    public StaffEntity updateStaff(@PathVariable int staffId, @RequestBody StaffEntity staff) {
        return staffService.updateStaff(staffId, staff);
    }

    @DeleteMapping("/{staffId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted staff")
    public String deleteStaff(@PathVariable int staffId) {
        return staffService.deleteStaff(staffId);
    }
}
