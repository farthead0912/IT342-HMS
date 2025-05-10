package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.junctions.PatientEquipment;
import edu.cit.hms.repository.PatientEquipmentRepository;

@Service
public class PatientEquipmentService {
    @Autowired
    private PatientEquipmentRepository patientEquipmentRepository;

    public PatientEquipment createPatientEquipment(PatientEquipment patientEquipment) {
        return patientEquipmentRepository.save(patientEquipment);
    }

    public PatientEquipment getPatientEquipmentById(int paeqId) {
        return patientEquipmentRepository.findById(paeqId).orElse(null);
    }

    public List<PatientEquipment> getAllPatientEquipments() {
        return patientEquipmentRepository.findAll();
    }

    public PatientEquipment updatePatientEquipment(int paeqId, PatientEquipment newPatientEquipment) {
        PatientEquipment patientEquipment = patientEquipmentRepository.findById(paeqId)
                .orElseThrow(() -> new RuntimeException("PatientEquipment ID: " + paeqId + " not found!"));

        if (newPatientEquipment.getEquipment() != null) {
            patientEquipment.setEquipment(newPatientEquipment.getEquipment());
        }
        if (newPatientEquipment.getPatient() != null) {
            patientEquipment.setPatient(newPatientEquipment.getPatient());
        }
        if (newPatientEquipment.getRoom() != null) {
            patientEquipment.setRoom(newPatientEquipment.getRoom());
        }

        return patientEquipmentRepository.save(patientEquipment);
    }

    public String deletePatientEquipment(int paeqId) {
        Optional<PatientEquipment> patientEquipment = patientEquipmentRepository.findById(paeqId);

        if (patientEquipment.isPresent()) {
            patientEquipmentRepository.deleteById(paeqId);
            return "PatientEquipment ID: " + paeqId + " deleted successfully!";
        } else {
            throw new RuntimeException("PatientEquipment ID: " + paeqId + " not found!");
        }
    }
}
