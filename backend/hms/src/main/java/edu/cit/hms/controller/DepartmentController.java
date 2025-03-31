package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.service.DepartmentService;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved departments"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<DepartmentEntity> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{deptId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved department"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DepartmentEntity getDepartmentById(@PathVariable int deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    @PostMapping("/")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created department"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{deptId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated department"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public DepartmentEntity updateDepartment(@PathVariable int deptId, @RequestBody DepartmentEntity department) {
        return departmentService.updateDepartment(deptId, department);
    }

    @DeleteMapping("/{deptId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully deleted department"),
            @ApiResponse(responseCode = "404", description = "Department not found"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public String deleteDepartment(@PathVariable int deptId) {
        return departmentService.deleteDepartment(deptId);
    }
}
