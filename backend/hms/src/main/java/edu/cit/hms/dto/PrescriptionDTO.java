package edu.cit.hms.dto;

import java.util.Date;

public class PrescriptionDTO {
    private int prescriptionId;
    private int doctorId;
    private int patientId;
    private Date prescribedAt;
    private String notes;

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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