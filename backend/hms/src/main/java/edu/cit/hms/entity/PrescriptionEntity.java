package edu.cit.hms.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "prescriptions")
public class PrescriptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prescriptionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctorId", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patient;

    @Column(nullable = false)
    private Date prescribedAt;

    @Column(nullable = false)
    private String notes;

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public DoctorEntity getDoctor() {
        return doctor;
    }

    public void setDoctor(DoctorEntity doctor) {
        this.doctor = doctor;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public Date getPrescribedAt() {
        return prescribedAt;
    }

    public void setPrescribedAt(Date prescribedAt) {
        this.prescribedAt = prescribedAt;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}