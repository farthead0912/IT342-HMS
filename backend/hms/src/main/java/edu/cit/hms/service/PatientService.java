package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.PatientDTO;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.repository.AdmissionRepository;
import edu.cit.hms.repository.PatientRecordRepository;
import edu.cit.hms.repository.PatientRepository;
import edu.cit.hms.repository.RoomRepository;
import edu.cit.hms.repository.UserRepository;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRecordRepository patientRecordRepository;

    public PatientEntity createPatient(PatientDTO patientDTO) {
        PatientEntity patient = convertFromDTO(patientDTO);

        return patientRepository.save(patient);
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

    private PatientEntity convertFromDTO(PatientDTO patientDTO) {
        PatientEntity patient = new PatientEntity();

        patient.setPatientId(patientDTO.getPatientId());
        patient.setFirstName(patientDTO.getFirstName());
        patient.setLastName(patientDTO.getLastName());
        patient.setGender(patientDTO.getGender());
        patient.setAge(patientDTO.getAge());
        patient.setBloodType(patientDTO.getBloodType());
        patient.setUser(userRepository.findById(patientDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User ID: " + patientDTO.getUserId() + " not found!")));
        patient.setPatientRecords(patientRecordRepository.findAllById(patientDTO.getPatientRecordIds()));
        patient.setAdmissions(admissionRepository.findAllById(patientDTO.getAdmissionIds()));
        // No need to fetch assignedEquipment as it's not handled here.

        return patient;
    }

    private PatientDTO convertToDTO(PatientEntity patient) {
        PatientDTO patientDTO = new PatientDTO();

        patientDTO.setPatientId(patient.getPatientId());
        patientDTO.setFirstName(patient.getFirstName());
        patientDTO.setLastName(patient.getLastName());
        patientDTO.setGender(patient.getGender());
        patientDTO.setAge(patient.getAge());
        patientDTO.setBloodType(patient.getBloodType());
        patientDTO.setUserId(patient.getUser() != null ? patient.getUser().getUserId() : 0);
        patientDTO.setPatientRecordIds(patient.getPatientRecords().stream()
                .map(record -> record.getRecordId()).toList());
        patientDTO.setAdmissionIds(patient.getAdmissions().stream()
                .map(admission -> admission.getAdmissionId()).toList());
        patientDTO.setAssignedEquipmentIds(patient.getAssignedEquipment().stream()
                .map(equipment -> equipment.getEquipment().getEquipmentId()) // Extract the ID
                .toList());

        return patientDTO;
    }

    public List<PatientDTO> getPatientDTOs() {
        List<PatientEntity> patients = patientRepository.findAll();
        
        return patients.stream()
                .map(this::convertToDTO)
                .toList();
    }
}