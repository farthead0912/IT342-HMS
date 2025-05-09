package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

    public AdmissionDTO createAdmission(AdmissionDTO admission) {
        AdmissionEntity admissionEntity = admissionRepository.save(convertFromDTO(admission));

        return convertToDTO(admissionEntity);
    }

    public AdmissionDTO getAdmissionById(int admissionId) {
        Optional<AdmissionEntity> admission = admissionRepository.findById(admissionId);

        return admission.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!"));
    }

    public List<AdmissionDTO> getAdmissions() {
        try {
            List<AdmissionEntity> admissions = admissionRepository.findAll();
            return admissions.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving admissions: " + e.getMessage());
            throw new RuntimeException("Error retrieving admissions at this moment. Please try again later.");
        }
    }

    public List<AdmissionDTO> getAdmissionsByPatientId(int patientId) {
        PatientEntity patient = patientRepository.findById(patientId)
            .orElseThrow(() -> new RuntimeException("Patient ID: " + patientId + " not found!"));

        List<AdmissionEntity> admissions = admissionRepository.findByPatient(patient);
        
        return admissions.stream().map(this::convertToDTO).toList();
    }

    public AdmissionDTO updateAdmission(int admissionId, AdmissionDTO newAdmission) {
        AdmissionEntity admission = admissionRepository.findById(admissionId)
            .orElseThrow(() -> new RuntimeException("Admission ID: " + admissionId + " not found!"));

        // Validate and update fields
        if (newAdmission.getDoctorId() > 0) {
            DoctorEntity doctor = doctorRepository.findById(newAdmission.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor ID: " + newAdmission.getDoctorId() + " not found!"));
            admission.setDoctor(doctor);
        }
        if (newAdmission.getPatientId() > 0) {
            PatientEntity patient = patientRepository.findById(newAdmission.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient ID: " + newAdmission.getPatientId() + " not found!"));
            admission.setPatient(patient);
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

        return convertToDTO(admissionRepository.save(admission));
    }

    public String deleteAdmission(int admissionId) {
        if(admissionRepository.existsById(admissionId)) {
            admissionRepository.deleteById(admissionId);

            return "Admission ID: " + admissionId + " deleted successfully!";
        } else {
            throw new RuntimeException("Admission ID: " + admissionId + " not found!");
        }
    }

    private AdmissionDTO convertToDTO(AdmissionEntity admission) {
        AdmissionDTO admissionDTO = new AdmissionDTO();

        admissionDTO.setAdmissionId(admission.getAdmissionId());
        admissionDTO.setDoctorId(admission.getDoctor().getDoctorId());
        admissionDTO.setPatientId(admission.getPatient().getPatientId());
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

        admission.setAdmissionId(admissionDTO.getAdmissionId());
        admission.setDoctor(doctor);
        admission.setPatient(patient);
        admission.setAdmissionDate(admissionDTO.getAdmissionDate());
        admission.setDischargeDate(admissionDTO.getDischargeDate());
        admission.setAdmissionReason(admissionDTO.getAdmissionReason());

        return admission;
    }
}
