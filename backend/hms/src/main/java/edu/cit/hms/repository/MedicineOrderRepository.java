package edu.cit.hms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.cit.hms.junctions.MedicineOrder;

@Repository
public interface MedicineOrderRepository extends JpaRepository<MedicineOrder, Integer> {
    List<MedicineOrder> findByPatient_PatientId(int patientId);
    List<MedicineOrder> findByDoctor_DoctorId(int doctorId);
    List<MedicineOrder> findByPrescription_PrescriptionId(int prescriptionId);
}
