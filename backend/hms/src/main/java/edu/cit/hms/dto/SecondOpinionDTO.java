package edu.cit.hms.dto;

import java.util.Date;

public class SecondOpinionDTO {
    private int opinionId;
    private int patientId;
    private int requestingDoctorId;
    private int secondDoctorId;
    private String reason;
    private String secondOpinionNotes;
    private Date requestedAt;
    private Date respondedAt;
    private String status = "Pending"; // Pending, Approved, Rejected

    public int getOpinionId() {
        return opinionId;
    }

    public void setOpinionId(int opinionId) {
        this.opinionId = opinionId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRequestingDoctorId() {
        return requestingDoctorId;
    }

    public void setRequestingDoctorId(int requestingDoctorId) {
        this.requestingDoctorId = requestingDoctorId;
    }

    public int getSecondDoctorId() {
        return secondDoctorId;
    }

    public void setSecondDoctorId(int secondDoctorId) {
        this.secondDoctorId = secondDoctorId;
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
