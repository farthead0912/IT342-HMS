package edu.cit.hms.junctions;

import edu.cit.hms.entity.DepartmentEntity;
import edu.cit.hms.entity.EquipmentEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "DepartmentEquipment")
public class DepartmentEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "equipmentId", nullable = false)
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "deptId", nullable = false)
    private DepartmentEntity department;

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }
}