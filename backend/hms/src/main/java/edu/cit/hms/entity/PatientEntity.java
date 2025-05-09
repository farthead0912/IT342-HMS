package edu.cit.hms.entity;

import java.util.List;

import edu.cit.hms.junctions.PatientEquipment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "First name is required!")
    private String firstName;

    @Column(nullable = false, length = 50)
    @NotBlank(message = "Last name is required!")
    private String lastName;

    @Column(nullable = false, length = 3)
    @NotBlank(message = "Age is also required.")
    private int age;

    @Column(nullable = false, length = 10)
    private String gender = "Rather not say";

    @Column(nullable = false, length = 3)
    @NotBlank(message = "You can't exactly leave this blank either.")
    @Pattern(regexp = "^(A|B|AB|O)[+-]$", message = "Invalid blood type format")
    // Blood type format: A+, A-, B+, B-, AB+, AB-, O+, O-
    private String bloodType;

    // this is OneToOne since one patient can only have one user account
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    // this is OneToMany since one patient can have many records
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<PatientRecordEntity> patientRecords;

    // this is OneToMany since one patient can have many admissions
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<AdmissionEntity> admissions;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Column(nullable = true)
    private List<PatientEquipment> assignedEquipment;

    // Getters and Setters
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getBloodType() {
        return bloodType;
    }

    public void setBloodType(String bloodType) {
        this.bloodType = bloodType;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public List<PatientRecordEntity> getPatientRecords() {
        return patientRecords;
    }

    public void setPatientRecords(List<PatientRecordEntity> patientRecords) {
        this.patientRecords = patientRecords;
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissions;
    }

    public void setAdmissions(List<AdmissionEntity> admissions) {
        this.admissions = admissions;
    }

    public List<PatientEquipment> getAssignedEquipment() {
        return assignedEquipment;
    }

    public void setAssignedEquipment(List<PatientEquipment> assignedEquipment) {
        this.assignedEquipment = assignedEquipment;
    }
}
