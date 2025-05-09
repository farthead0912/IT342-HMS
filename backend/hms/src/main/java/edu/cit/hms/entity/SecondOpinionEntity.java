package edu.cit.hms.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "second_opinions")
public class SecondOpinionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int opinionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "requestingDoctorId", nullable = false)
    private DoctorEntity requestingDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secondDoctorId", nullable = false)
    private DoctorEntity secondDoctor;

    @Column(nullable = false, length = 255)
    private String reason;

    @Column(nullable = true, length = 255)
    private String secondOpinionNotes;

    @Column(nullable = false)
    private Date requestedAt;

    @Column(nullable = true)
    private Date respondedAt;

    @Column(nullable = false)
    private String status = "Pending"; // Pending, Approved, Rejected

    public int getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(int opinionId) {
        this.opinionId = opinionId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public DoctorEntity getRequestingDoctor() {
        return requestingDoctor;
    }

    public void setRequestingDoctor(DoctorEntity requestingDoctor) {
        this.requestingDoctor = requestingDoctor;
    }

    public DoctorEntity getSecondDoctor() {
        return secondDoctor;
    }

    public void setSecondDoctor(DoctorEntity secondDoctor) {
        this.secondDoctor = secondDoctor;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSecondOpinionNotes() {
        return secondOpinionNotes;
    }

    public void setSecondOpinionNotes(String secondOpinionNotes) {
        this.secondOpinionNotes = secondOpinionNotes;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Date getRespondedAt() {
        return respondedAt;
    }

    public void setRespondedAt(Date respondedAt) {
        this.respondedAt = respondedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}