package edu.cit.hms.dto;

public class PatientEquipmentDTO {
    private int paeqId;
    private Integer equipmentId; // Changed from EquipmentEntity to int
    private int patientId;   // Changed from PatientEntity to int
    private Integer roomId;  // Changed from RoomEntity to Integer (nullable)

    // Getters and Setters
    public int getPaeqId() {
        return paeqId;
    }

    public void setPaeqId(int paeqId) {
        this.paeqId = paeqId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(Integer equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public Integer getRoomId() {
        return roomId;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }
}
