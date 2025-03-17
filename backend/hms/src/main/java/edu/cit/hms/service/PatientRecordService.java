package edu.cit.hms.service;

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
}
