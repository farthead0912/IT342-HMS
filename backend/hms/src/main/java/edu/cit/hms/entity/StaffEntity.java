package edu.cit.hms.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class StaffEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int staffId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String position;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptId")
    private DepartmentEntity department;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private UserEntity user;

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

    public DepartmentEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentEntity department) {
        this.department = department;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
