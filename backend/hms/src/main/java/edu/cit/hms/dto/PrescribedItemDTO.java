package edu.cit.hms.dto;

public class PrescribedItemDTO {
    private int itemId;
    private int prescriptionId;
    private String medicineName;
    private String dosage;
    private String frequency;
    private String duration;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(int prescriptionId) {
        this.prescriptionId = prescriptionId;
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