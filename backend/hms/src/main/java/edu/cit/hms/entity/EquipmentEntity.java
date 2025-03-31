package edu.cit.hms.entity;

import jakarta.persistence.*;
import java.util.Set;
import java.util.List;

import edu.cit.hms.junctions.PatientEquipment;

@Entity
@Table(name = "equipment")
public class EquipmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int equipmentId;

    @Column(nullable = false, length = 50)
    private String equipmentName;
    
    @Column(nullable = false, length = 50)
    private String equipmentType;

    @Column(nullable = false, length = 5)
    private int stock;

    @Column(nullable = false, length = 10)
    private double price;
    
    @Column(nullable = false, length = 50)
    private String status = "Available";

    // this is ManyToOne because many equipment can be used in one room
    @ManyToOne
    @JoinColumn(name = "roomId", nullable = true)
    private RoomEntity room;

    // ManyToMany relationship with DepartmentEntity
    @ManyToMany
    @JoinTable(
        name = "DepartmentEquipment",
        joinColumns = @JoinColumn(name = "equipmentId", nullable = false),
        inverseJoinColumns = @JoinColumn(name = "deptId", nullable = false)
    )
    private Set<DepartmentEntity> departments;

    @OneToMany(mappedBy = "equipment", cascade = CascadeType.ALL)
    private List<PatientEquipment> patientUsage;

    // Constructor
    public EquipmentEntity() {
        this.status = "Available"; // Ensure default value is set in the constructor
    }

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

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public Set<DepartmentEntity> getDepartments() {
        return departments;
    }

    public void setDepartments(Set<DepartmentEntity> departments) {
        this.departments = departments;
    }
}