package edu.cit.hms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.junctions.DepartmentEquipment;

@Repository
public interface DepartmentEquipmentRepository extends JpaRepository<DepartmentEquipment, Integer> {
    
}
