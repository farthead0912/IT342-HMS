package edu.cit.hms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    public DepartmentEntity createDepartment(DepartmentEntity department) {
        return departmentRepository.save(department);
    }

    public DepartmentEntity getDepartmentById(int deptId) {
        return departmentRepository.findById(deptId).orElse(null);
    }

    public List<DepartmentEntity> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<DepartmentEntity> getDepartment(int deptId) {
        return departmentRepository.findById(deptId);
    }

    public DepartmentEntity updateDepartment(int deptId, DepartmentEntity newDept) {
        DepartmentEntity department;

        try {
            department = departmentRepository.findById(deptId)
                .orElseThrow(() -> new RuntimeException("Department ID: " + deptId + " not found!"));

            department.setDeptName(newDept.getDeptName());

            return departmentRepository.save(department);
        } catch (NoSuchElementException nex) {
            throw new RuntimeException("Department ID: " + deptId + " not found!");
        }
    }

    public void deleteDepartment(int deptId) {
        Optional<DepartmentEntity> department = departmentRepository.findById(deptId);

        if(department.isPresent()) {
            departmentRepository.deleteById(deptId);
        } else {
            throw new RuntimeException("Department ID: " + deptId + " not found!");
        }
    }
}
