package edu.cit.hms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;

    @Column(nullable = false, length = 50)
    private String roomType;

    @Column(nullable = false, length = 3)
    private int roomNumber;

    @Column(nullable = false, length = 10)
    private double roomPrice;

    @Column(nullable = false, length = 2)
    private int floorNumber;

    @Column(nullable = false)
    private boolean isOccupied = false;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "staffId", nullable = true)
    private StaffEntity staff;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    private List<AdmissionEntity> admissions;

    // Getters and Setters
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(int floorNumber) {
        this.floorNumber = floorNumber;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean isOccupied) {
        this.isOccupied = isOccupied;
    }

    public StaffEntity getStaff() {
        return staff;
    }

    public void setStaff(StaffEntity staff) {
        this.staff = staff;
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }
}
