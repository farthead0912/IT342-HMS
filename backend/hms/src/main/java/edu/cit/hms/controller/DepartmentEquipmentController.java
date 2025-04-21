package edu.cit.hms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.junctions.DepartmentEquipment;
import edu.cit.hms.service.DepartmentEquipmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "DepartmentEquipment not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')") // Class-level restriction
@RestController
@RequestMapping(value = "/api/department-equipment", consumes = "application/json", produces = "application/json")
public class DepartmentEquipmentController {

    @Autowired
    private DepartmentEquipmentService departmentEquipmentService;

    @PostMapping(value = "/")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can create
    @ApiResponse(responseCode = "201", description = "Successfully created DepartmentEquipment")
    public ResponseEntity<DepartmentEquipment> createDepartmentEquipment(@RequestBody DepartmentEquipment departmentEquipment) {
        return ResponseEntity.status(201).body(departmentEquipmentService.createDepartmentEquipment(departmentEquipment));
    }

    @GetMapping(value = "/{id}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved DepartmentEquipment")
    public ResponseEntity<DepartmentEquipment> getDepartmentEquipmentById(@PathVariable int id) {
        return ResponseEntity.ok(departmentEquipmentService.getDepartmentEquipmentById(id));
    }

    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved all DepartmentEquipments")
    public ResponseEntity<List<DepartmentEquipment>> getAllDepartmentEquipments() {
        return ResponseEntity.ok(departmentEquipmentService.getAllDepartmentEquipments());
    }

    @PutMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can update
    @ApiResponse(responseCode = "200", description = "Successfully updated DepartmentEquipment")
    public ResponseEntity<DepartmentEquipment> updateDepartmentEquipment(@PathVariable int id, @RequestBody DepartmentEquipment departmentEquipment) {
        return ResponseEntity.ok(departmentEquipmentService.updateDepartmentEquipment(id, departmentEquipment));
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete
    @ApiResponse(responseCode = "200", description = "Successfully deleted DepartmentEquipment")
    public ResponseEntity<String> deleteDepartmentEquipment(@PathVariable int id) {
        return ResponseEntity.ok(departmentEquipmentService.deleteDepartmentEquipment(id));
    }
}
