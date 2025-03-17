package edu.cit.hms.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "patients")
public class PatientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int patientId;

    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String bloodType;

    // this is ManyToOne since many patients can be in one room, like in some hospitals with 3 beds per room
    @ManyToOne
    @JoinColumn(name = "roomId")
    private RoomEntity room;

    // this is OneToOne since one patient can only have one user account
    @OneToOne
    @JoinColumn(name = "userId")
    private UserEntity user;

    // this is OneToMany since one patient can have many records
    @OneToMany(mappedBy = "patient")
    private List<PatientRecordEntity> patientRecords;

    // this is OneToMany since one patient can have many schedules
    @OneToMany(mappedBy = "patient")
    private List<ScheduleEntity> schedules;

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

    public RoomEntity getRoom() {
        return room;
    }
    
    public void setRoom(RoomEntity room) {
        this.room = room;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}
