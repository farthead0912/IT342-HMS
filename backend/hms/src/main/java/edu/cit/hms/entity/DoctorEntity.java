package edu.cit.hms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "doctors")
public class DoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int doctorId;

    @Column(nullable = false, length = 50)
    private String firstName;

    @Column(nullable = false, length = 50)
    private String lastName;

    @Column(nullable = false, length = 50)
    private String specialization;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "deptId")
    private DepartmentEntity department;

    // this is OneToOne since one doctor has one user, duh
    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    // this is OneToMany since one doctor can have many schedules for many patients, also setting nullable to true since doctor might not have any schedules (or schedule has been finished)
    @OneToMany(mappedBy = "doctor")
    private List<AdmissionEntity> admissions;

    // Getters and Setters
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
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

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
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

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }
}
