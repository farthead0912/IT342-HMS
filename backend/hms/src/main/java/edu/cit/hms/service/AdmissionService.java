package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.AdmissionDTO;
import edu.cit.hms.entity.AdmissionEntity;
import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.entity.RoomEntity;
import edu.cit.hms.repository.AdmissionRepository;
import edu.cit.hms.repository.DoctorRepository;
import edu.cit.hms.repository.PatientRepository;
import edu.cit.hms.repository.RoomRepository;

@Service
public class AdmissionService {
    @Autowired
    private AdmissionRepository admissionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private RoomRepository roomRepository;

    public AdmissionEntity createAdmission(AdmissionEntity admissionEntity) {
        try {
            return admissionRepository.save(admissionEntity);
        } catch (Exception e) {
            throw new RuntimeException("Error creating admission", e);
        }
    }

    public AdmissionEntity getAdmissionById(int admissionId) {
        return admissionRepository.findById(admissionId)
            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!"));
    }

    public List<AdmissionEntity> getAdmissions() {
        return admissionRepository.findAll();
    }

    public AdmissionEntity updateAdmission(int admissionId, AdmissionEntity newAdmission) {
        AdmissionEntity admission = admissionRepository.findById(admissionId)
            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!"));

        // Validate and update fields
        if (newAdmission.getDoctor() != null) {
            admission.setDoctor(newAdmission.getDoctor());
        }
        if (newAdmission.getPatient() != null) {
            admission.setPatient(newAdmission.getPatient());
        }
        if (newAdmission.getRoom() != null) {
            admission.setRoom(newAdmission.getRoom());
        }
        if (newAdmission.getAdmissionDate() != null) {
            admission.setAdmissionDate(newAdmission.getAdmissionDate());
        }
        if (newAdmission.getDischargeDate() != null) {
            admission.setDischargeDate(newAdmission.getDischargeDate());
        }
        if (newAdmission.getAdmissionReason() != null) {
            admission.setAdmissionReason(newAdmission.getAdmissionReason());
        }

        return admissionRepository.save(admission);
    }

    public void deleteAdmission(int admissionId) {
        AdmissionEntity admission = admissionRepository.findById(admissionId)
            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!"));
        admissionRepository.delete(admission);
    }

    private AdmissionDTO convertToDTO(AdmissionEntity admission) {
        AdmissionDTO admissionDTO = new AdmissionDTO();

        admissionDTO.setAdmissionId(admission.getAdmissionId());
        admissionDTO.setDoctorId(admission.getDoctor().getDoctorId());
        admissionDTO.setPatientId(admission.getPatient().getPatientId());
        admissionDTO.setRoomId(admission.getRoom().getRoomId());
        admissionDTO.setAdmissionDate(admission.getAdmissionDate());
        admissionDTO.setDischargeDate(admission.getDischargeDate());
        admissionDTO.setAdmissionReason(admission.getAdmissionReason());

        return admissionDTO;
    }

    private AdmissionEntity convertFromDTO(AdmissionDTO admissionDTO) {
        AdmissionEntity admission = new AdmissionEntity();
        DoctorEntity doctor = doctorRepository.findById(admissionDTO.getDoctorId())
            .orElseThrow(() -> new RuntimeException("Doctor ID: " + admissionDTO.getDoctorId() + " not found!"));
        PatientEntity patient = patientRepository.findById(admissionDTO.getPatientId())
            .orElseThrow(() -> new RuntimeException("Patient ID: " + admissionDTO.getPatientId() + " not found!"));
        RoomEntity room = roomRepository.findById(admissionDTO.getRoomId())
            .orElseThrow(() -> new RuntimeException("Room ID: " + admissionDTO.getRoomId() + " not found!"));

        admission.setAdmissionId(admissionDTO.getAdmissionId());
        admission.setDoctor(doctor);
        admission.setPatient(patient);
        admission.setRoom(room);
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setDischargeDate(admissionDTO.getDischargeDate());
        admission.setAdmissionReason(admissionDTO.getAdmissionReason());

        return admission;
    }
}
