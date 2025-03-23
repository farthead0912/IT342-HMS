package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.PatientRecordEntity;
import edu.cit.hms.repository.PatientRecordRepository;

@Service
public class PatientRecordService {
    @Autowired
    private PatientRecordRepository patientRecordRepository;

    public PatientRecordEntity createPatientRecord(PatientRecordEntity patientRecordEntity) {
        return patientRecordRepository.save(patientRecordEntity);
    }

    public PatientRecordEntity getPatientRecordById(int patientRecordId) {
        return patientRecordRepository.findById(patientRecordId).orElse(null);
    }

    public List<PatientRecordEntity> getPatientRecords() {
        return patientRecordRepository.findAll();
    }

    public PatientRecordEntity updatePatientRecord(int patientRecordId, PatientRecordEntity newPatientRecord) {
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
        return patientRecordRepository.save(patientRecord);
    }

    public void deletePatientRecord(int patientRecordId) {
        Optional<PatientRecordEntity> patientRecord = patientRecordRepository.findById(patientRecordId);

        if(patientRecord.isPresent()) {
            patientRecordRepository.deleteById(patientRecordId);
        } else {
            throw new RuntimeException("Patient Record ID: " + patientRecordId + " not found!");
        }
    }
}
