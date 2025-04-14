package edu.cit.hms.dto;

public class StaffDTO {
    private int staffId;
    private String firstName;
    private String lastName;
    private String position;
    private int departmentId;
    private int userId;

    // Empty Constructor
    public StaffDTO() {}

    // Constructor
    public StaffDTO(int staffId, String firstName, String lastName, String position, int departmentId, int userId) {
        this.staffId = staffId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.departmentId = departmentId;
        this.userId = userId;
    }

    // Getters and Setters
    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
