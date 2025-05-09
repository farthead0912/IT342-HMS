package edu.cit.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.entity.PrescribedItem;

@Repository
public interface PrescribedItemRepository extends JpaRepository<PrescribedItem, Integer> {
    List<PrescribedItem> findByPrescription_PrescriptionId(int prescriptionId);
}
