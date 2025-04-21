package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.service.DepartmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Department not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')") // Class-level restriction
@RestController
@RequestMapping(value = "/api/department", consumes = "application/json", produces = "application/json")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    
    // Gets all departments
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved departments")
    public ResponseEntity<List<DepartmentEntity>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.getAllDepartments());
    }

    // Gets department by ID
    @GetMapping(value = "/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved department")
    public ResponseEntity<DepartmentEntity> getDepartmentById(@PathVariable int deptId) {
        return ResponseEntity.ok(departmentService.getDepartmentById(deptId));
    }

    // Creates a new department
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create departments
    @PostMapping(value = "/")
    @ApiResponse(responseCode = "201", description = "Successfully created department")
    public ResponseEntity<DepartmentEntity> createDepartment(@RequestBody DepartmentEntity department) {
        return ResponseEntity.status(201).body(departmentService.createDepartment(department));
    }

    // Updates department details by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update departments
    @PutMapping(value = "/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated department")
    public ResponseEntity<DepartmentEntity> updateDepartment(@PathVariable int deptId, @RequestBody DepartmentEntity department) {
        return ResponseEntity.ok(departmentService.updateDepartment(deptId, department));
    }

    // Deletes department by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete departments
    @DeleteMapping(value = "/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted department")
    public ResponseEntity<String> deleteDepartment(@PathVariable int deptId) {
        return ResponseEntity.ok(departmentService.deleteDepartment(deptId));
    }
}
