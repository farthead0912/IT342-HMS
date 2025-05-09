package edu.cit.hms.junctions;

import java.util.Date;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.PrescriptionEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "medicine_orders")
public class MedicineOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescriptionId", nullable = false)
    private PrescriptionEntity prescription;

    @Column(nullable = false)
    private Date orderedAt;

    @Column(nullable = false)
    private String status = "Pending"; // Pending, Fulfilled, Cancelled

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public PrescriptionEntity getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionEntity prescription) {
        this.prescription = prescription;
    }

    public Date getOrderedAt() {
        return orderedAt;
    }

    public void setOrderedAt(Date orderedAt) {
        this.orderedAt = orderedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}