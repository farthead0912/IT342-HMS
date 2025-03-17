package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.*;

@Repository
public interface DoctorRepository extends JpaRepository<DoctorEntity, Integer> {
    public List<DoctorEntity> findByFirstName(String firstName);
    public List<DoctorEntity> findByLastName(String lastName);
    public List<DoctorEntity> findBySpecialization(String specialization);

    public Optional<DoctorEntity> findBySchedule(ScheduleEntity schedule);
    public Optional<DoctorEntity> findByUser(UserEntity user);
    public Optional<DoctorEntity> findByDepartment(DepartmentEntity department);
}
