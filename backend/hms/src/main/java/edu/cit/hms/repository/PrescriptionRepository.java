package edu.cit.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.entity.PrescriptionEntity;

@Repository
public interface PrescriptionRepository extends JpaRepository<PrescriptionEntity, Integer> {
    List<PrescriptionEntity> findByPatient_PatientId(int patientId);
    List<PrescriptionEntity> findByDoctor_DoctorId(int doctorId);
}
