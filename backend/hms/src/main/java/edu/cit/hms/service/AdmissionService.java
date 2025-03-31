package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.cit.hms.entity.AdmissionEntity;
import edu.cit.hms.repository.AdmissionRepository;

@Service
public class AdmissionService {
    @Autowired
    private AdmissionRepository admissionRepository;

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
}
