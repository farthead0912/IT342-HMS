package edu.cit.hms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.BillingDTO;
import edu.cit.hms.entity.BillingEntity;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.entity.StaffEntity;
import edu.cit.hms.repository.*;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private StaffRepository staffRepository;

    @Autowired
    private RoomRepository roomRepository;

    public BillingEntity createBilling(BillingEntity billing) {
        return billingRepository.save(billing);
    }

    public BillingEntity getBillingById(int billingId) {
        return billingRepository.findById(billingId).orElse(null);
    }

    public List<BillingEntity> getBillings() {
        return billingRepository.findAll();
    }

    public BillingEntity updateBilling(int billingId, BillingEntity newBilling) {
        try {
            // Find the existing billing record
            BillingEntity billing = billingRepository.findById(billingId)
                .orElseThrow(() -> new RuntimeException("Billing ID: " + billingId + " not found!"));

            // Update fields if they are not null or valid
            if (newBilling.getAmount() > 0) {
                billing.setAmount(newBilling.getAmount());
            }

            if (newBilling.getIssuedAt() != null) {
                billing.setIssuedAt(newBilling.getIssuedAt());
            }

            if (newBilling.getPatient() != null) {
                billing.setPatient(newBilling.getPatient());
            }

            if (newBilling.getRoom() != null) {
                billing.setRoom(newBilling.getRoom());
            }

            // Save and return the updated billing record
            return billingRepository.save(billing);
        } catch (NoSuchElementException nex) {
            throw new RuntimeException("Billing ID: " + billingId + " not found!");
        }
    }

    public String deleteBilling(int billingId) {
        Optional<BillingEntity> billing = billingRepository.findById(billingId);

        if (billing.isPresent()) {
            billingRepository.deleteById(billingId);
            return "Billing ID: " + billingId + " deleted successfully!";
        } else {
            throw new RuntimeException("Billing ID: " + billingId + " not found!");
        }
    }

    private BillingEntity convertFromDTO(BillingDTO billingDTO) {
        BillingEntity billing = new BillingEntity();
        PatientEntity patient = patientRepository.findById(billingDTO.getPatientId())
            .orElseThrow(() -> new RuntimeException("Patient ID: " + billingDTO.getPatientId() + " not found!"));
        StaffEntity staff = staffRepository.findById(billingDTO.getStaffId())
            .orElseThrow(() -> new RuntimeException("Staff ID: " + billingDTO.getStaffId() + " not found!"));
        RoomEntity room = roomRepository.findById(billingDTO.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room ID: " + billingDTO.getRoomId() + " not found!"));

        billing.setBillId(billingDTO.getBillId());
        billing.setPatient(patient);
        billing.setStaff(staff);
        billing.setIssuedAt(billingDTO.getIssuedAt());
        billing.setPatient(patient);
        billing.setRoom(room);
        billing.setAmount(billingDTO.getAmount());

        return billing;
    }

    private BillingDTO convertToDTO(BillingEntity billing) {
        return new BillingDTO(
            billing.getBillId(),
            billing.getPatient().getPatientId(),
            billing.getStaff().getStaffId(),
            billing.getRoom().getRoomId(),
            billing.getAmount(),
            billing.getIssuedAt()
        );
    }
}
