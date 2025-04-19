package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<AdmissionEntity> createAdmission(AdmissionEntity admissionEntity) {
        try {
            AdmissionEntity createdAdmission = admissionRepository.save(admissionEntity);
            return new ResponseEntity<>(createdAdmission, HttpStatus.CREATED); // 201 Created
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    public ResponseEntity<AdmissionEntity> getAdmissionById(int admissionId) {
        Optional<AdmissionEntity> admission = admissionRepository.findById(admissionId);
        if (admission.isPresent()) {
            return new ResponseEntity<>(admission.get(), HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    public ResponseEntity<List<AdmissionEntity>> getAdmissions() {
        try {
            List<AdmissionEntity> admissions = admissionRepository.findAll();
            return new ResponseEntity<>(admissions, HttpStatus.OK); // 200 OK
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500 Internal Server Error
        }
    }

    public ResponseEntity<AdmissionEntity> updateAdmission(int admissionId, AdmissionEntity newAdmission) {
        Optional<AdmissionEntity> admissionOptional = admissionRepository.findById(admissionId);
        if (admissionOptional.isPresent()) {
            AdmissionEntity admission = admissionOptional.get();

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

            AdmissionEntity updatedAdmission = admissionRepository.save(admission);
            return new ResponseEntity<>(updatedAdmission, HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404 Not Found
        }
    }

    public ResponseEntity<String> deleteAdmission(int admissionId) {
        Optional<AdmissionEntity> admission = admissionRepository.findById(admissionId);
        if (admission.isPresent()) {
            admissionRepository.delete(admission.get());
            return new ResponseEntity<>("Admission ID: " + admissionId + " deleted successfully!", HttpStatus.OK); // 200 OK
        } else {
            return new ResponseEntity<>("Admission ID: " + admissionId + " not found!", HttpStatus.NOT_FOUND); // 404 Not Found
        }
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
