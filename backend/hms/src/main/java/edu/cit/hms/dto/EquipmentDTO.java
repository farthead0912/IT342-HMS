package edu.cit.hms.dto;

public class EquipmentDTO {
    private int equipmentId;
    private String equipmentName;
    private String equipmentType;
    private int stock;
    private double price;
    private String status;
    private int roomId;

    // Constructor
    public EquipmentDTO(int equipmentId, String equipmentName, String equipmentType, int stock, double price, String status, int roomId) {
        this.equipmentId = equipmentId;
        this.equipmentName = equipmentName;
        this.equipmentType = equipmentType;
        this.stock = stock;
        this.price = price;
        this.status = status;
        this.roomId = roomId;
    }

    // Empty Constructor
    public EquipmentDTO() {}

    // Getters and Setters
    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }
}
