package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.AdmissionEntity;

@Repository
public interface AdmissionRepository extends JpaRepository<AdmissionEntity, Integer> {
    public Optional<AdmissionEntity> findByDoctor(DoctorEntity doctor);
    public List<AdmissionEntity> findByPatient(PatientEntity patient);

    public List<AdmissionEntity> findByStatus(String status);
}
