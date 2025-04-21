package edu.cit.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.junctions.PatientEquipment;

@Repository
public interface PatientEquipmentRepository extends JpaRepository<PatientEquipment, Integer> {
}
