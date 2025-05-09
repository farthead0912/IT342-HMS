package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.PrescriptionDTO;
import edu.cit.hms.entity.PrescriptionEntity;
import edu.cit.hms.repository.DoctorRepository;
import edu.cit.hms.repository.PatientRepository;
import edu.cit.hms.repository.PrescriptionRepository;

@Service
public class PrescriptionService {
    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    public PrescriptionDTO createPrescription(PrescriptionDTO prescription) {
        if (prescription.getDoctorId() > 0 && doctorRepository.findById(prescription.getDoctorId()).isEmpty()) {
            throw new RuntimeException("Doctor ID: " + prescription.getDoctorId() + " not found!");
        }
        if (prescription.getPatientId() > 0 && patientRepository.findById(prescription.getPatientId()).isEmpty()) {
            throw new RuntimeException("Patient ID: " + prescription.getPatientId() + " not found!");
        }

        return convertToDTO(prescriptionRepository.save(convertFromDTO(prescription)));
    }

    public PrescriptionDTO getPrescriptionById(int prescriptionId) {
        Optional<PrescriptionEntity> prescription = prescriptionRepository.findById(prescriptionId);

        return prescription.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Prescription ID: " + prescriptionId + " not found!"));
    }

    public List<PrescriptionDTO> getPrescriptionByPatientId(int patientId) {
        List<PrescriptionEntity> prescriptions = prescriptionRepository.findByPatient_PatientId(patientId);

        if (prescriptions.isEmpty()) {
            throw new RuntimeException("No prescriptions found for Patient ID: " + patientId);
        }

        return prescriptions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<PrescriptionDTO> getPrescriptionsByDoctorId(int doctorId) {
        List<PrescriptionEntity> prescriptions = prescriptionRepository.findByDoctor_DoctorId(doctorId);

        if (prescriptions.isEmpty()) {
            throw new RuntimeException("No prescriptions found for Doctor ID: " + doctorId);
        }

        return prescriptions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public List<PrescriptionDTO> getAllPrescriptions() {
        List<PrescriptionEntity> prescriptions = prescriptionRepository.findAll();

        if (prescriptions.isEmpty()) {
            throw new RuntimeException("No prescriptions found in the database.");
        }

        return prescriptions.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PrescriptionDTO updatePrescription(PrescriptionDTO newPrescription) {
        PrescriptionEntity prescription = prescriptionRepository.findById(newPrescription.getPrescriptionId())
                .orElseThrow(() -> new RuntimeException("Prescription ID: " + newPrescription.getPrescriptionId() + " not found!"));

        // Validate and update fields
        if (newPrescription.getDoctorId() > 0) {
            prescription.setDoctor(doctorRepository.findById(newPrescription.getDoctorId())
                    .orElseThrow(() -> new RuntimeException("Doctor ID: " + newPrescription.getDoctorId() + " not found!")));
        }
        if (newPrescription.getPatientId() > 0) {
            prescription.setPatient(patientRepository.findById(newPrescription.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + newPrescription.getPatientId() + " not found!")));
        }
        if (newPrescription.getPrescribedAt() != null) {
            prescription.setPrescribedAt(newPrescription.getPrescribedAt());
        }
        if (newPrescription.getNotes() != null && !newPrescription.getNotes().isEmpty()) {
            prescription.setNotes(newPrescription.getNotes());
        }

        return convertToDTO(prescriptionRepository.save(prescription));
    }

    public String deletePrescription(int prescriptionId) {
        Optional<PrescriptionEntity> prescription = prescriptionRepository.findById(prescriptionId);

        if (prescription.isPresent()) {
            prescriptionRepository.deleteById(prescriptionId);
            return "Prescription ID: " + prescriptionId + " deleted successfully!";
        } else {
            throw new RuntimeException("Prescription ID: " + prescriptionId + " not found!");
        }
    }

    public PrescriptionDTO convertToDTO(PrescriptionEntity prescription) {
        PrescriptionDTO prescriptionDTO = new PrescriptionDTO();

        prescriptionDTO.setPrescriptionId(prescription.getPrescriptionId());
        prescriptionDTO.setDoctorId(prescription.getDoctor().getDoctorId());
        prescriptionDTO.setPatientId(prescription.getPatient().getPatientId());
        prescriptionDTO.setPrescribedAt(prescription.getPrescribedAt());
        prescriptionDTO.setNotes(prescription.getNotes());

        return prescriptionDTO;
    }

    public PrescriptionEntity convertFromDTO(PrescriptionDTO prescriptionDTO) {
        PrescriptionEntity prescription = new PrescriptionEntity();

        prescription.setPrescriptionId(prescriptionDTO.getPrescriptionId());

        if(prescriptionDTO.getDoctorId() > 0) {
            prescription.setDoctor(doctorRepository.findById(prescriptionDTO.getDoctorId()).orElse(null));
        }
        if(prescriptionDTO.getPatientId() > 0) {
            prescription.setPatient(patientRepository.findById(prescriptionDTO.getPatientId()).orElse(null));
        }

        prescription.setPrescribedAt(prescriptionDTO.getPrescribedAt());
        prescription.setNotes(prescriptionDTO.getNotes());

        return prescription;
    }
}
