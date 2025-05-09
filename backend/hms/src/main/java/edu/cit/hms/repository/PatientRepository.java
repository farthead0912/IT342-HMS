package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.*;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Integer> {
    public List<PatientEntity> findByFirstName(String firstName);
    public List<PatientEntity> findByLastName(String lastName);
    public List<PatientEntity> findByAge(int age);
    public List<PatientEntity> findByGender(String gender);
    public List<PatientEntity> findByBloodType(String bloodType);
    
    public Optional<PatientEntity> findByUser(UserEntity user);
    public List<PatientEntity> findByPatientRecords(PatientRecordEntity patientRecord);
    public List<PatientEntity> findByAdmissions(AdmissionEntity admissions);
}
