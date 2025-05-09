package edu.cit.hms.dto;

import java.util.List;

public class PatientDTO {
    private int patientId;
    private String firstName;
    private String lastName;
    private int age;
    private String gender;
    private String bloodType;
    private int userId;
    private List<Integer> patientRecordIds;
    private List<Integer> admissionIds;
    private List<Integer> assignedEquipmentIds;

    // Constructor
    public PatientDTO(int patientId, String firstName, String lastName, int age, String gender, String bloodType, int userId, List<Integer> patientRecordIds, List<Integer> admissionIds, List<Integer> assignedEquipmentIds) {
        this.patientId = patientId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.gender = gender;
        this.bloodType = bloodType;
        this.userId = userId;
        this.patientRecordIds = patientRecordIds;
        this.admissionIds = admissionIds;
        this.assignedEquipmentIds = assignedEquipmentIds;
    }

    // Empty Constructor
    public PatientDTO() {}

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public List<Integer> getPatientRecordIds() {
        return patientRecordIds;
    }

    public void setPatientRecordIds(List<Integer> patientRecordIds) {
        this.patientRecordIds = patientRecordIds;
    }

    public List<Integer> getAdmissionIds() {
        return admissionIds;
    }

    public void setAdmissionIds(List<Integer> admissionIds) {
        this.admissionIds = admissionIds;
    }

    public List<Integer> getAssignedEquipmentIds() {
        return assignedEquipmentIds;
    }

    public void setAssignedEquipmentIds(List<Integer> assignedEquipmentIds) {
        this.assignedEquipmentIds = assignedEquipmentIds;
    }
}