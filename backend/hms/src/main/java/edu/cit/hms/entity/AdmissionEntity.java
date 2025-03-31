package edu.cit.hms.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "admissions")
public class AdmissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int admissionId;

    @ManyToOne
    @JoinColumn(name = "doctorId", nullable = false)
    private DoctorEntity doctor;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = true)
    private RoomEntity room;

    @Column(nullable = true)
    private Date admissionDate;

    @Column(nullable = true)
    private Date dischargeDate;

    @Column(nullable = true, length = 255)
    private String admissionReason;

    @Column(nullable = true, length = 10)
    private String status;

    // Getters and Setters
    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
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

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Date getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(Date admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
    }

    public String getAdmissionReason() {
        return admissionReason;
    }

    public void setAdmissionReason(String admissionReason) {
        this.admissionReason = admissionReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
