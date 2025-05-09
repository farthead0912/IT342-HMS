package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.DoctorDTO;
import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.entity.UserEntity;
import edu.cit.hms.repository.DepartmentRepository;
import edu.cit.hms.repository.DoctorRepository;
import edu.cit.hms.repository.UserRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private UserRepository userRepository;
    
    public DoctorDTO createDoctor(DoctorDTO doctorDTO) {
        DoctorEntity doctor = convertFromDTO(doctorDTO);
        DoctorEntity savedDoctor = doctorRepository.save(doctor);

        return convertToDTO(savedDoctor);
    }

    public DoctorDTO getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Doctor ID: " + doctorId + " not found!"));
    }

    public List<DoctorDTO> getDoctorByFirstName(String firstName) {
        List<DoctorEntity> doctors = doctorRepository.findByFirstName(firstName);
        return doctors.stream().map(this::convertToDTO).toList();
    }

    public List<DoctorDTO> getDoctorByLastName(String lastName) {
        List<DoctorEntity> doctors = doctorRepository.findByLastName(lastName);
        return doctors.stream().map(this::convertToDTO).toList();
    }

    public List<DoctorDTO> getDoctorBySpecialization(String specialization) {
        List<DoctorEntity> doctors = doctorRepository.findBySpecialization(specialization);
        return doctors.stream().map(this::convertToDTO).toList();
    }

    public List<DoctorDTO> getDoctorByDepartmentId(int departmentId) {
        DepartmentEntity department = departmentRepository.findById(departmentId)
                .orElseThrow(() -> new RuntimeException("Department ID: " + departmentId + " not found!"));
        List<DoctorEntity> doctors = doctorRepository.findByDepartment(department);
        return doctors.stream().map(this::convertToDTO).toList();
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream()
                .map(this::convertToDTO)
                .toList();
    }

    public DoctorDTO updateDoctor(int doctorId, DoctorDTO doctorDTO) {
        DoctorEntity doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor ID: " + doctorId + " not found!"));

        if (doctorDTO.getFirstName() != null && !doctorDTO.getFirstName().isEmpty()) {
            doctor.setFirstName(doctorDTO.getFirstName());
        }
        if (doctorDTO.getLastName() != null && !doctorDTO.getLastName().isEmpty()) {
            doctor.setLastName(doctorDTO.getLastName());
        }
        if (doctorDTO.getSpecialization() != null && !doctorDTO.getSpecialization().isEmpty()) {
            doctor.setSpecialization(doctorDTO.getSpecialization());
        }

        if (doctorDTO.getDepartmentId() > 0) {
            DepartmentEntity department = departmentRepository.findById(doctorDTO.getDepartmentId())
                    .orElseThrow(() -> new RuntimeException("Department ID: " + doctorDTO.getDepartmentId() + " not found!"));
            doctor.setDepartment(department);
        }

        if (doctorDTO.getUserId() > 0) {
            UserEntity user = userRepository.findById(doctorDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User ID: " + doctorDTO.getUserId() + " not found!"));
            doctor.setUser(user);
        }

        return convertToDTO(doctorRepository.save(doctor));
    }

    public String deleteDoctor(int doctorId) {
        if(doctorRepository.existsById(doctorId)) {
            doctorRepository.deleteById(doctorId);

            return "Doctor ID: " + doctorId + " deleted successfully!";
        } else {
            throw new RuntimeException("Doctor ID: " + doctorId + " not found!");
        }
    }

    public DoctorEntity convertFromDTO(DoctorDTO doctorDTO) {
        DoctorEntity doctor = new DoctorEntity();
        DepartmentEntity department = departmentRepository.findById(doctorDTO.getDepartmentId())
            .orElseThrow(() -> new RuntimeException("Department not found!"));
        UserEntity user = userRepository.findById(doctorDTO.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found!"));

        doctor.setDoctorId(doctorDTO.getDoctorId());
        doctor.setFirstName(doctorDTO.getFirstName());
        doctor.setLastName(doctorDTO.getLastName());
        doctor.setSpecialization(doctorDTO.getSpecialization());
        doctor.setDepartment(department);
        doctor.setUser(user);

        return doctor;
    }

    public DoctorDTO convertToDTO(DoctorEntity doctor) {
        return new DoctorDTO(
            doctor.getDoctorId(),
            doctor.getFirstName(),
            doctor.getLastName(),
            doctor.getSpecialization(),
            doctor.getDepartment().getDeptId(),
            doctor.getUser().getUserId()
        );
    }

    public List<DoctorDTO> getDoctorDTOs() {
        List<DoctorEntity> doctors = doctorRepository.findAll();

        return doctors.stream()
            .map(this::convertToDTO)
            .toList();
    }

    public DoctorDTO getDoctorDTOById(int doctorId) {
        return doctorRepository.findById(doctorId)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }
}