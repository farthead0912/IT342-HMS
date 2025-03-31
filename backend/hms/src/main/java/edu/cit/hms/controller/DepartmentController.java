package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RestController
@RequestMapping("/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    
    @GetMapping("/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved departments")
    public List<DepartmentEntity> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved department")
    public DepartmentEntity getDepartmentById(@PathVariable int deptId) {
        return departmentService.getDepartmentById(deptId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created department")
    public DepartmentEntity createDepartment(@RequestBody DepartmentEntity department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated department")
    public DepartmentEntity updateDepartment(@PathVariable int deptId, @RequestBody DepartmentEntity department) {
        return departmentService.updateDepartment(deptId, department);
    }

    @DeleteMapping("/{deptId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted department")
    public String deleteDepartment(@PathVariable int deptId) {
        return departmentService.deleteDepartment(deptId);
    }
}
