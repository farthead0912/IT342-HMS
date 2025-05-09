package edu.cit.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.MedicineOrderDTO;
import edu.cit.hms.junctions.MedicineOrder;
import edu.cit.hms.repository.MedicineOrderRepository;
import edu.cit.hms.repository.PatientRepository;
import edu.cit.hms.repository.PrescriptionRepository;

@Service
public class MedicineOrderService {
    @Autowired
    private MedicineOrderRepository medicineOrderRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public MedicineOrderDTO createMedicineOrder(MedicineOrderDTO medicineOrder) {
        return convertToDTO(medicineOrderRepository.save(convertFromDTO(medicineOrder)));
    }

    public MedicineOrderDTO getMedicineOrderById(int orderId) {
        return medicineOrderRepository.findById(orderId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Medicine Order ID: " + orderId + " not found!"));
    }

    public List<MedicineOrderDTO> getAllMedicineOrders() {
        return medicineOrderRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<MedicineOrderDTO> getMedicineOrdersByPatientId(int patientId) {
        List<MedicineOrder> medicineOrders = medicineOrderRepository.findByPatient_PatientId(patientId);

        if (medicineOrders.isEmpty()) {
            throw new RuntimeException("No medicine orders found for Patient ID: " + patientId);
        }

        return medicineOrders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<MedicineOrderDTO> getMedicineOrdersByPrescriptionId(int prescriptionId) {
        List<MedicineOrder> medicineOrders = medicineOrderRepository.findByPrescription_PrescriptionId(prescriptionId);

        if (medicineOrders.isEmpty()) {
            throw new RuntimeException("No medicine orders found for Prescription ID: " + prescriptionId);
        }

        return medicineOrders.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public MedicineOrderDTO updateMedicineOrder(int orderId, MedicineOrderDTO newMedicineOrder) {
        MedicineOrder medicineOrder = medicineOrderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Medicine Order ID: " + orderId + " not found!"));

        if (newMedicineOrder.getPrescriptionId() > 0) {
            medicineOrder.setPrescription(prescriptionRepository.findById(newMedicineOrder.getPrescriptionId())
                    .orElseThrow(() -> new RuntimeException("Prescription ID: " + newMedicineOrder.getPrescriptionId() + " not found!")));
        }
        if (newMedicineOrder.getPatientId() > 0) {
            medicineOrder.setPatient(patientRepository.findById(newMedicineOrder.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + newMedicineOrder.getPatientId() + " not found!")));
        }
        if (newMedicineOrder.getOrderedAt() != null) {
            medicineOrder.setOrderedAt(newMedicineOrder.getOrderedAt());
        }
        if (newMedicineOrder.getStatus() != null) {
            medicineOrder.setStatus(newMedicineOrder.getStatus());
        }

        return convertToDTO(medicineOrderRepository.save(medicineOrder));
    }

    public String deleteMedicineOrder(int orderId) {
        if (medicineOrderRepository.existsById(orderId)) {
            medicineOrderRepository.deleteById(orderId);
            return "Medicine Order ID: " + orderId + " deleted successfully!";
        } else {
            throw new RuntimeException("Medicine Order ID: " + orderId + " not found!");
        }
    }

    public MedicineOrderDTO convertToDTO(MedicineOrder medicineOrder) {
        MedicineOrderDTO medicineOrderDTO = new MedicineOrderDTO();

        medicineOrderDTO.setOrderId(medicineOrder.getOrderId());
        medicineOrderDTO.setPrescriptionId(medicineOrder.getPrescription().getPrescriptionId());
        medicineOrderDTO.setPatientId(medicineOrder.getPatient().getPatientId());
        medicineOrderDTO.setOrderedAt(medicineOrder.getOrderedAt());
        medicineOrderDTO.setStatus(medicineOrder.getStatus());

        return medicineOrderDTO;
    }

    public MedicineOrder convertFromDTO(MedicineOrderDTO medicineOrderDTO) {
        MedicineOrder medicineOrder = new MedicineOrder();

        medicineOrder.setOrderId(medicineOrderDTO.getOrderId());
        medicineOrder.setPrescription(prescriptionRepository.findById(medicineOrderDTO.getPrescriptionId())
                .orElseThrow(() -> new RuntimeException("Prescription ID: " + medicineOrderDTO.getPrescriptionId() + " not found!")));
        medicineOrder.setPatient(patientRepository.findById(medicineOrderDTO.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient ID: " + medicineOrderDTO.getPatientId() + " not found!")));
        medicineOrder.setOrderedAt(medicineOrderDTO.getOrderedAt());
        medicineOrder.setStatus(medicineOrderDTO.getStatus());

        return medicineOrder;
    }
}