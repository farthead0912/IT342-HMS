package edu.cit.hms.junctions;

import jakarta.persistence.*;

import edu.cit.hms.entity.EquipmentEntity;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.RoomEntity;

@Entity
@Table(name = "patient_equipment")
public class PatientEquipment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paeqId;

    @ManyToOne
    @JoinColumn(name = "equipmentId", nullable = true) // Allow null values
    private EquipmentEntity equipment;

    @ManyToOne
    @JoinColumn(name = "patientId", nullable = false) // Patient is required
    private PatientEntity patient;

    @ManyToOne
    @JoinColumn(name = "roomId", nullable = true) // Allow null values for room
    private RoomEntity room;

    // Getters and Setters
    public int getPaeqId() {
        return paeqId;
    }

    public void setPaeqId(int paeqId) {
        this.paeqId = paeqId;
    }

    public EquipmentEntity getEquipment() {
        return equipment;
    }

    public void setEquipment(EquipmentEntity equipment) {
        this.equipment = equipment;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public RoomEntity getRoom() {
        return room;
    }

    public void setRoom(RoomEntity room) {
        this.room = room;
    }
}
