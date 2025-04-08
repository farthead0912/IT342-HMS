package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.repository.StaffRepository;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    public StaffEntity createStaff(StaffEntity staff) {
        return staffRepository.save(staff);
    }

    public StaffEntity getStaffById(int staffId) {
        return staffRepository.findById(staffId).orElse(null);
    }

    public List<StaffEntity> getStaff() {
        return staffRepository.findAll();
    }

    public StaffEntity updateStaff(int staffId, StaffEntity newStaff) {
        StaffEntity staff = staffRepository.findById(staffId)
            .orElseThrow(() -> new RuntimeException("Staff ID: " + staffId + " not found!"));

        // Validate new data
        if (staff.getFirstName() != null) {
            staff.setFirstName(newStaff.getFirstName());
        }
        if (staff.getLastName() != null) {
            staff.setLastName(newStaff.getLastName());
        }
        if (staff.getPosition() != null) {
            staff.setPosition(newStaff.getPosition());
        }
        if (staff.getDepartment() != null) {
            staff.setDepartment(newStaff.getDepartment());
        }
        if (staff.getUser() != null) {
            staff.setUser(newStaff.getUser());
        }

        return staffRepository.save(staff);
    }

    public String deleteStaff(int staffId) {
        Optional<StaffEntity> staff = staffRepository.findById(staffId);

        if (staff.isPresent()) {
            staffRepository.deleteById(staffId);

            return "Staff ID: " + staffId + " deleted successfully!";
        } else {
            throw new RuntimeException("Staff ID: " + staffId + " not found!");
        }
    }
}
