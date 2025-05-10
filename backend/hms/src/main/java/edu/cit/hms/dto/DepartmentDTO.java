package edu.cit.hms.dto;

public class DepartmentDTO {
    private int deptId;
    private String deptName;

    // Empty Constructor
    public DepartmentDTO() {}

    // Constructor
    public DepartmentDTO(int deptId, String deptName) {
        this.deptId = deptId;
        this.deptName = deptName;
    }

    // Getters and Setters
    public int getDeptId() {
        return deptId;
    }

    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
