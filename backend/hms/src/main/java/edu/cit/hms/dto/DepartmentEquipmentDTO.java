package edu.cit.hms.dto;

public class DepartmentEquipmentDTO {
    private int deeqId;
    private int equipmentId;
    private int departmentId;

    // Getters and Setters
    public int getDeeqId() {
        return deeqId;
    }

    public void setDeeqId(int deeqId) {
        this.deeqId = deeqId;
    }

    public int getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(int equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }
}
