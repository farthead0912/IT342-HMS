package edu.cit.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.entity.SecondOpinionEntity;

@Repository
public interface SecondOpinionRepository extends JpaRepository<SecondOpinionEntity, Integer> {
    List<SecondOpinionEntity> findByPatient_PatientId(int patientId);
    List<SecondOpinionEntity> findBySecondDoctor_DoctorId(int doctorId);
}
