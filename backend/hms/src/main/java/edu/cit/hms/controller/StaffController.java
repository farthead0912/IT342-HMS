package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.service.StaffService;

@RestController
@RequestMapping("/api/staff")
public class StaffController {
    @Autowired
    private StaffService staffService;

    @GetMapping("/all")
    public List<StaffEntity> getStaff() {
        return staffService.getStaff();
    }

    @GetMapping("/{id}")
    public StaffEntity getStaffById(@PathVariable int id) {
        return staffService.getStaffById(id);
    }

    @PostMapping("/add")
    public StaffEntity createStaff(@RequestBody StaffEntity staff) {
        return staffService.createStaff(staff);
    }

    @PutMapping("/update/{id}")
    public StaffEntity updateStaff(@PathVariable int staffId, @RequestBody StaffEntity staff) {
        return staffService.updateStaff(staffId, staff);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteStaff(@PathVariable int id) {
        return staffService.deleteStaff(id);
    }
}
