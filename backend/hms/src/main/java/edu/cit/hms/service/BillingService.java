package edu.cit.hms.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.BillingEntity;
import edu.cit.hms.repository.BillingRepository;

@Service
public class BillingService {
    @Autowired
    private BillingRepository billingRepository;

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
}
