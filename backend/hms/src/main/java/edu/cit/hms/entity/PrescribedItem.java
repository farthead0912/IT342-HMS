package edu.cit.hms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "prescribed_items")
public class PrescribedItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int itemId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prescriptionId", nullable = false)
    private PrescriptionEntity prescription;

    @Column(nullable = false)
    private String medicineName;

    @Column(nullable = false)
    private String dosage;

    @Column(nullable = false)
    private String frequency;

    @Column(nullable = false)
    private String duration;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public PrescriptionEntity getPrescription() {
        return prescription;
    }

    public void setPrescription(PrescriptionEntity prescription) {
        this.prescription = prescription;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}