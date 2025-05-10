package edu.cit.hms.entity;

import java.util.Set;

import jakarta.persistence.*;

@Entity
@Table(name = "departments")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptId;

    @Column(nullable = false, length = 50)
    private String deptName;

    @ManyToMany(mappedBy = "departments", fetch = FetchType.LAZY)
    private Set<EquipmentEntity> equipments;

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
