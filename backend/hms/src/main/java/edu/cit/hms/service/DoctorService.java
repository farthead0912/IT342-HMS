package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.repository.DoctorRepository;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    
    public DoctorEntity createDoctor(DoctorEntity doctor) {
        return doctorRepository.save(doctor);
    }

    public DoctorEntity getDoctorById(int doctorId) {
        return doctorRepository.findById(doctorId).orElse(null);
    }

    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Optional<DoctorEntity> getDoctor(int doctorId) {
        return doctorRepository.findById(doctorId);
    }

    public DoctorEntity updateDoctor(int doctorId, DoctorEntity newDoctor) {
        DoctorEntity doctor;

        try {
            doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor ID: " + doctorId + " not found!"));

            if(doctor.getFirstName() != null && newDoctor.getFirstName().isEmpty()) {
                doctor.setFirstName(newDoctor.getFirstName());
            }
            if(doctor.getLastName() != null && newDoctor.getLastName().isEmpty()) {
                doctor.setLastName(newDoctor.getLastName());
            }
            if(doctor.getSpecialization() != null && newDoctor.getSpecialization().isEmpty()) {
                doctor.setSpecialization(newDoctor.getSpecialization());
            }
            if(doctor.getDepartment() != null && newDoctor.getDepartment() != null) {
                doctor.setDepartment(newDoctor.getDepartment());
            }
            if(doctor.getUser() != null && newDoctor.getUser() != null) {
                doctor.setUser(newDoctor.getUser());
            }

            return doctorRepository.save(doctor);
        } catch (NoSuchElementException nex) {
            throw new RuntimeException("Doctor ID: " + doctorId + " not found!");
        }
    }

    public String deleteDoctor(int doctorId) {
        Optional<DoctorEntity> doctor = doctorRepository.findById(doctorId);

        if(doctor.isPresent()) {
            doctorRepository.deleteById(doctorId);

            return "Doctor ID: " + doctorId + " deleted successfully!";
        } else {
            throw new RuntimeException("Doctor ID: " + doctorId + " not found!");
        }
    }
}
