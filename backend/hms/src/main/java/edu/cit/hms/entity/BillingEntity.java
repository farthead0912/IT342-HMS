package edu.cit.hms.entity;

import java.sql.Date;

import jakarta.persistence.*;

@Entity
@Table(name = "billing")
public class BillingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billId;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false)
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = false)
    private RoomEntity room;

    private double amount;

    private Date issuedAt;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public Date getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(Date issuedAt) {
        this.issuedAt = issuedAt;
    }
}
