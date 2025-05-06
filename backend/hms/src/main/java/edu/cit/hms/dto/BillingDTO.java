package edu.cit.hms.dto;

import java.sql.Date;

public class BillingDTO {
    private int billId;
    private int patientId;
    private int staffId;
    private int roomId;
    private double amount;
    private Date issuedAt;

    // Empty Constructor
    public BillingDTO() {}

    // Constructor
    public BillingDTO(int billId, int patientId, int staffId, int roomId, double amount, Date issuedAt) {
        this.billId = billId;
        this.patientId = patientId;
        this.staffId = staffId;
        this.roomId = roomId;
        this.amount = amount;
        this.issuedAt = issuedAt;
    }

    // Getters and Setters
    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
