package edu.cit.hms.dto;

import java.util.Date;

public class PatientRecordDTO {
    private int recordId;
    private int patientId; // Assuming patientId is sufficient for DTO
    private String sickness;
    private Date diagnosisDate;
    private String severity;
    private String treatmentPlan;

    // Empty Constructor
    public PatientRecordDTO() {}

    // Constructor
    public PatientRecordDTO(int recordId, int patientId, String sickness, Date diagnosisDate, String severity, String treatmentPlan) {
        this.recordId = recordId;
        this.patientId = patientId;
        this.sickness = sickness;
        this.diagnosisDate = diagnosisDate;
        this.severity = severity;
        this.treatmentPlan = treatmentPlan;
    }

    // Getters and Setters
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getSickness() {
        return sickness;
    }

    public void setSickness(String sickness) {
        this.sickness = sickness;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }

    public String getTreatmentPlan() {
        return treatmentPlan;
    }

    public void setTreatmentPlan(String treatmentPlan) {
        this.treatmentPlan = treatmentPlan;
    }
}
