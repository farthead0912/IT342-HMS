package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.PatientRecordDTO;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.PatientRecordEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.repository.PatientRecordRepository;
import edu.cit.hms.repository.PatientRepository;

@Service
public class PatientRecordService {
    @Autowired
    private PatientRecordRepository patientRecordRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    public PatientRecordDTO createPatientRecord(PatientRecordDTO patientRecordDTO) {
        PatientRecordEntity patientRecord = patientRecordRepository.save(convertFromDTO(patientRecordDTO));

        return convertToDTO(patientRecord);
    }

    public PatientRecordDTO getPatientRecordById(int patientRecordId) {
        Optional<PatientRecordEntity> patientRecord = patientRecordRepository.findById(patientRecordId);

        return patientRecord.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Patient Record ID: " + patientRecordId + " not found!"));
    }

    public PatientRecordDTO getPatientRecordByPatientId(int patientId) {
        PatientEntity patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient ID: " + patientId + " not found!"));

        return patientRecordRepository.findByPatient(patient)
            .map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("No patient record found for Patient ID: " + patientId));
    }

    public List<PatientRecordDTO> getPatientRecords() {
        try {
            List<PatientRecordEntity> patientRecords = patientRecordRepository.findAll();
            return patientRecords.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving rooms: " + e.getMessage());
            throw new RuntimeException("Error retrieving rooms at this moment. Please try again later.");
        }
    }

    public PatientRecordDTO updatePatientRecord(int patientRecordId, PatientRecordDTO newPatientRecord) {
        PatientRecordEntity patientRecord = patientRecordRepository.findById(patientRecordId)
                .orElseThrow(() -> new RuntimeException("Patient Record ID: " + patientRecordId + " not found!"));

        // Validate new data
        if(newPatientRecord.getSickness() != null && !newPatientRecord.getSickness().isEmpty()) {
            patientRecord.setSickness(newPatientRecord.getSickness());
        }
        if(newPatientRecord.getDiagnosisDate() != null && newPatientRecord.getDiagnosisDate().after(patientRecord.getDiagnosisDate())) {
            patientRecord.setDiagnosisDate(newPatientRecord.getDiagnosisDate());
        }
        if(newPatientRecord.getSeverity() != null && !newPatientRecord.getSeverity().isEmpty()) {
            patientRecord.setSeverity(newPatientRecord.getSeverity());
        }
        if(newPatientRecord.getTreatmentPlan() != null && !newPatientRecord.getTreatmentPlan().isEmpty()) {
            patientRecord.setTreatmentPlan(newPatientRecord.getTreatmentPlan());
        }

        // Save the updated record
        return convertToDTO(patientRecordRepository.save(patientRecord));
    }

    public String deletePatientRecord(int patientRecordId) {
        Optional<PatientRecordEntity> patientRecord = patientRecordRepository.findById(patientRecordId);

        if(patientRecord.isPresent()) {
            patientRecordRepository.deleteById(patientRecordId);

            return "Patient Record ID: " + patientRecordId + " deleted successfully!";
        } else {
            throw new RuntimeException("Patient Record ID: " + patientRecordId + " not found!");
        }
    }

    private PatientRecordEntity convertFromDTO(PatientRecordDTO patientRecordDTO) {
        PatientRecordEntity patientRecord = new PatientRecordEntity();
        
        patientRecord.setSickness(patientRecordDTO.getSickness());
        patientRecord.setDiagnosisDate(patientRecordDTO.getDiagnosisDate());
        patientRecord.setSeverity(patientRecordDTO.getSeverity());
        patientRecord.setTreatmentPlan(patientRecordDTO.getTreatmentPlan());

        return patientRecord;
    }

    private PatientRecordDTO convertToDTO(PatientRecordEntity patientRecord) {
        return new PatientRecordDTO(
                patientRecord.getRecordId(),
                patientRecord.getPatient().getPatientId(),
                patientRecord.getSickness(),
                patientRecord.getDiagnosisDate(),
                patientRecord.getSeverity(),
                patientRecord.getTreatmentPlan()
        );
    }
}
