package edu.cit.hms.repository;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

import edu.cit.hms.entity.StaffEntity;

@Repository
public interface StaffRepository extends JpaRepository<StaffEntity, Integer> {
    public Optional<StaffEntity> findByFirstName(String firstName);
    public Optional<StaffEntity> findByLastName(String lastName);
    public List<StaffEntity> findByPosition(String position);
}
