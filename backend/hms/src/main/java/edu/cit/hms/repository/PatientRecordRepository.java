package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.PatientRecordEntity;

@Repository
public interface PatientRecordRepository extends JpaRepository<PatientRecordEntity, Integer> {
    public Optional<PatientRecordEntity> findByPatient(PatientEntity patient);
    public List<PatientRecordEntity> findBySickness(String sickness);
    public List<PatientRecordEntity> findBySeverity(String severity);
    public List<PatientRecordEntity> findByTreatmentPlan(String treatmentPlan);
    public List<PatientRecordEntity> findByDiagnosisDate(Date diagnosisDate);
    public Optional<PatientRecordEntity> findByPatientId(int patientId);
}
