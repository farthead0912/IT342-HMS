package edu.cit.hms.dto;

import java.util.List;

public class RoomDTO {
    private int roomId;
    private String roomType;
    private int roomNumber;
    private double roomPrice;
    private int floorNumber;
    private boolean isOccupied = false;
    private int patientId; // Assuming only IDs are needed for relations
    private int staffId;
    private List<Integer> admissionIdList;

    public RoomDTO() {}

    public RoomDTO(int roomId, String roomType, int roomNumber, double roomPrice, int floorNumber, boolean isOccupied, int patientId, int staffId, List<Integer> admissionIdList) {
        this.roomId = roomId;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.floorNumber = floorNumber;
        this.isOccupied = isOccupied;
        this.patientId = patientId;
        this.staffId = staffId;
        this.admissionIdList = admissionIdList;
    }

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

    public List<Integer> getAdmissionIdList() {
        return admissionIdList;
    }

    public void setAdmissionIdList(List<Integer> admissionIdList) {
        this.admissionIdList = admissionIdList;
    }
}
