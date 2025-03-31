package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.repository.PatientRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public PatientEntity createPatient(PatientEntity patientEntity) {
        return patientRepository.save(patientEntity);
    }

    public PatientEntity getPatientById(int patientId) {
        return patientRepository.findById(patientId).orElse(null);
    }

    public List<PatientEntity> getPatients() {
        return patientRepository.findAll();
    }

    public PatientEntity updatePatient(int patientId, PatientEntity newPatient) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient ID: " + patientId + " not found!"));

        // Validate new data
        if(newPatient.getFirstName() != null && !newPatient.getFirstName().isEmpty()) {
            patient.setFirstName(newPatient.getFirstName());
        }
        if(newPatient.getLastName() != null && !newPatient.getLastName().isEmpty()) {
            patient.setLastName(newPatient.getLastName());
        }
        if(newPatient.getGender() != null && !newPatient.getGender().isEmpty()) {
            patient.setGender(newPatient.getGender());
        }
        if(newPatient.getAge() > 0) {
            patient.setAge(newPatient.getAge());
        }
        if(newPatient.getGender() != null && !newPatient.getGender().isEmpty()) {
            patient.setGender(newPatient.getGender());
        }
        if(newPatient.getBloodType() != null && !newPatient.getBloodType().isEmpty()) {
            patient.setBloodType(newPatient.getBloodType());
        }
        if(newPatient.getRoom() != null) {
            patient.setRoom(newPatient.getRoom());
        }
        if(newPatient.getAdmissions() != null) {
            patient.setAdmissions(newPatient.getAdmissions());
        }
        if(newPatient.getPatientRecords() != null) {
            patient.setPatientRecords(newPatient.getPatientRecords());
        }
        if(newPatient.getUser() != null) {
            patient.setUser(newPatient.getUser());
        }

        return patientRepository.save(patient);
    }

    public String deletePatient(int patientId) {
        Optional<PatientEntity> patient = patientRepository.findById(patientId);

        if(patient.isPresent()) {
            patientRepository.deleteById(patientId);

            return "Patient ID: " + patientId + " deleted successfully!";
        } else {
            throw new RuntimeException("Patient ID: " + patientId + " not found!");
        }
    }
}
