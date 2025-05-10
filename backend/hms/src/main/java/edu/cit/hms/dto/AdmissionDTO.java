package edu.cit.hms.dto;

import java.util.Date;

public class AdmissionDTO {
    private int admissionId;
    private int doctorId;
    private int patientId;
    private int roomId; // Nullable
    private Date admissionDate;
    private Date dischargeDate;
    private String admissionReason;
    private String status;

    // Default constructor
    public AdmissionDTO() {}

    // Parameterized constructor
    public AdmissionDTO(int admissionId, int doctorId, int patientId, int roomId, Date admissionDate, Date dischargeDate, String admissionReason, String status) {
        this.admissionId = admissionId;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.roomId = roomId;
        this.admissionDate = admissionDate;
        this.dischargeDate = dischargeDate;
        this.admissionReason = admissionReason;
        this.status = status;
    }

    // Getters and Setters
    public int getAdmissionId() {
        return admissionId;
    }

    public void setAdmissionId(int admissionId) {
        this.admissionId = admissionId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
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
