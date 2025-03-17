package edu.cit.hms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptId;

    private String deptName;

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
