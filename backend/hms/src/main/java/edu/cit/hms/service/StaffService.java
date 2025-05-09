package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.StaffDTO;
import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.repository.DepartmentRepository;
import edu.cit.hms.repository.StaffRepository;
import edu.cit.hms.repository.UserRepository;

@Service
public class StaffService {
    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;

    public StaffDTO createStaff(StaffDTO staffDTO) {
        StaffEntity saved = staffRepository.save(convertFromDTO(staffDTO));
        return convertToDTO(saved);
    }

    public StaffDTO getStaffById(int staffId) {
        Optional<StaffEntity> staff = staffRepository.findById(staffId);

        return staff.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Staff ID: " + staffId + " not found!"));
    }

    public List<StaffDTO> getStaff() {
        try {
            List<StaffEntity> staff = staffRepository.findAll();

            return staff.stream()
                .map(this::convertToDTO)
                .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving staff: " + e.getMessage());
            throw new RuntimeException("Error retrieving staff at this moment. Please try again later.");
        }
    }

    public StaffDTO updateStaff(int staffId, StaffDTO newStaff) {
        StaffEntity staff = staffRepository.findById(staffId)
            .orElseThrow(() -> new RuntimeException("Staff ID: " + staffId + " not found!"));

        if (newStaff.getFirstName() != null) staff.setFirstName(newStaff.getFirstName());
        if (newStaff.getLastName() != null) staff.setLastName(newStaff.getLastName());
        if (newStaff.getPosition() != null) staff.setPosition(newStaff.getPosition());

        if (newStaff.getDepartmentId() > 0) {
            DepartmentEntity department = departmentRepository.findById(newStaff.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department ID: " + newStaff.getDepartmentId() + " not found!"));
            staff.setDepartment(department);
        }

        if (newStaff.getUserId() > 0) {
            UserEntity user = userRepository.findById(newStaff.getUserId())
                .orElseThrow(() -> new RuntimeException("User ID: " + newStaff.getUserId() + " not found!"));
            staff.setUser(user);
        }

        return convertToDTO(staffRepository.save(staff));
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

    private StaffEntity convertFromDTO(StaffDTO staffDTO) {
        StaffEntity staff = new StaffEntity();
        DepartmentEntity dept = departmentRepository.findById(staffDTO.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department not found!"));

        UserEntity user = userRepository.findById(staffDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found!"));

        staff.setFirstName(staffDTO.getFirstName());
        staff.setLastName(staffDTO.getLastName());
        staff.setPosition(staffDTO.getPosition());
        staff.setDepartment(dept);
        staff.setUser(user);

        return staff;
    }

    private StaffDTO convertToDTO(StaffEntity staff) {
        return new StaffDTO(
            staff.getStaffId(),
            staff.getFirstName(),
            staff.getLastName(),
            staff.getPosition(),
            staff.getDepartment().getDeptId(),
            staff.getUser().getUserId()
        );
    }
}