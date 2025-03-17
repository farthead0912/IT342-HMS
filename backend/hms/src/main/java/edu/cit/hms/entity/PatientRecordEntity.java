package edu.cit.hms.entity;

import java.util.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "patient_records")
public class PatientRecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int recordId;

    @ManyToOne
    @JoinColumn(name = "patientId")
    private PatientEntity patient;

    private String sickness;
    private Date diagnosisDate;
    private String severity;
    private String treatmentPlan;

    // Getters and Setters
    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
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