package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.ScheduleEntity;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Integer> {
    public Optional<ScheduleEntity> findByDoctor(DoctorEntity doctor);
    public Optional<ScheduleEntity> findByPatient(PatientEntity patient);
    public List<ScheduleEntity> findByStatus(String status);
}
