package edu.cit.hms.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.SecondOpinionDTO;
import edu.cit.hms.entity.SecondOpinionEntity;
import edu.cit.hms.repository.DoctorRepository;
import edu.cit.hms.repository.PatientRepository;
import edu.cit.hms.repository.SecondOpinionRepository;

@Service
public class SecondOpinionService {
    @Autowired
    private SecondOpinionRepository secondOpinionRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    public SecondOpinionDTO createSecondOpinion(SecondOpinionDTO secondOpinion) {
        return convertToDTO(secondOpinionRepository.save(convertFromDTO(secondOpinion)));
    }

    public List<SecondOpinionDTO> getAllSecondOpinions() {
        try {
            List<SecondOpinionEntity> secondOpinions = secondOpinionRepository.findAll();
            return secondOpinions.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving second opinions: " + e.getMessage());
            throw new RuntimeException("Error retrieving second opinions at this moment. Please try again later.");
        }
    }

    public SecondOpinionDTO getSecondOpinionById(int secondOpinionId) {
        Optional<SecondOpinionEntity> secondOpinion = secondOpinionRepository.findById(secondOpinionId);

        return secondOpinion.map(this::convertToDTO)
            .orElseThrow(() -> new RuntimeException("Second Opinion ID: " + secondOpinionId + " not found!"));
    }

    public List<SecondOpinionDTO> getSecondOpinionsByPatientId(int patientId) {
        try {
            List<SecondOpinionEntity> secondOpinions = secondOpinionRepository.findByPatient_PatientId(patientId);
            return secondOpinions.stream()
                    .map(this::convertToDTO)
                    .toList();
        } catch (DataAccessException e) {
            System.err.println("Error retrieving second opinions for " + patientId + ": " + e.getMessage());
            throw new RuntimeException("Error retrieving second opinions for " + patientId + " at this moment. Please try again later.");
        }
    }

    public SecondOpinionDTO updateSecondOpinion(int id, SecondOpinionDTO updatedSecondOpinion) {
        SecondOpinionEntity secondOpinion = secondOpinionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Second Opinion ID: " + id + " not found!"));

        if(updatedSecondOpinion.getPatientId() > 0) {
            secondOpinion.setPatient(patientRepository.findById(updatedSecondOpinion.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + updatedSecondOpinion.getPatientId() + " not found!")));
        }
        if(updatedSecondOpinion.getRequestingDoctorId() > 0) {
            secondOpinion.setRequestingDoctor(doctorRepository.findById(updatedSecondOpinion.getRequestingDoctorId())
                    .orElseThrow(() -> new RuntimeException("Requesting Doctor ID: " + updatedSecondOpinion.getRequestingDoctorId() + " not found!")));
        }
        if(updatedSecondOpinion.getSecondDoctorId() > 0) {
            secondOpinion.setSecondDoctor(doctorRepository.findById(updatedSecondOpinion.getSecondDoctorId())
                    .orElseThrow(() -> new RuntimeException("Second Doctor ID: " + updatedSecondOpinion.getSecondDoctorId() + " not found!")));
        }

        secondOpinion.setReason(updatedSecondOpinion.getReason());
        secondOpinion.setSecondOpinionNotes(updatedSecondOpinion.getSecondOpinionNotes());
        secondOpinion.setRequestedAt(updatedSecondOpinion.getRequestedAt());
        secondOpinion.setRespondedAt(updatedSecondOpinion.getRespondedAt());
        secondOpinion.setStatus(updatedSecondOpinion.getStatus());

        return convertToDTO(secondOpinionRepository.save(secondOpinion));
    }

    public String deleteSecondOpinion(int secondOpinionId) {
        Optional<SecondOpinionEntity> secondOpinion = secondOpinionRepository.findById(secondOpinionId);

        if (secondOpinion.isPresent()) {
            secondOpinionRepository.deleteById(secondOpinionId);
            return "Second Opinion ID: " + secondOpinionId + " deleted successfully!";
        } else {
            throw new RuntimeException("Second Opinion ID: " + secondOpinionId + " not found!");
        }
    }

    public SecondOpinionDTO convertToDTO(SecondOpinionEntity secondOpinion) {
        SecondOpinionDTO secondOpinionDTO = new SecondOpinionDTO();

        secondOpinionDTO.setOpinionId(secondOpinion.getOpinionId());
        secondOpinionDTO.setPatientId(secondOpinion.getPatient().getPatientId());
        secondOpinionDTO.setRequestingDoctorId(secondOpinion.getRequestingDoctor().getDoctorId());
        secondOpinionDTO.setSecondDoctorId(secondOpinion.getSecondDoctor().getDoctorId());
        secondOpinionDTO.setReason(secondOpinion.getReason());
        secondOpinionDTO.setSecondOpinionNotes(secondOpinion.getSecondOpinionNotes());
        secondOpinionDTO.setRequestedAt(secondOpinion.getRequestedAt());
        secondOpinionDTO.setRespondedAt(secondOpinion.getRespondedAt());
        secondOpinionDTO.setStatus(secondOpinion.getStatus());

        return secondOpinionDTO;
    }

    public SecondOpinionEntity convertFromDTO(SecondOpinionDTO secondOpinionDTO) {
        SecondOpinionEntity secondOpinion = new SecondOpinionEntity();

        secondOpinion.setOpinionId(secondOpinionDTO.getOpinionId());

        if(secondOpinionDTO.getPatientId() > 0) {
            secondOpinion.setPatient(patientRepository.findById(secondOpinionDTO.getPatientId())
                    .orElseThrow(() -> new RuntimeException("Patient ID: " + secondOpinionDTO.getPatientId() + " not found!")));
        }

        if(secondOpinionDTO.getRequestingDoctorId() > 0) {
            secondOpinion.setRequestingDoctor(doctorRepository.findById(secondOpinionDTO.getRequestingDoctorId())
                    .orElseThrow(() -> new RuntimeException("Requesting Doctor ID: " + secondOpinionDTO.getRequestingDoctorId() + " not found!")));
        }

        if(secondOpinionDTO.getSecondDoctorId() > 0) {
            secondOpinion.setSecondDoctor(doctorRepository.findById(secondOpinionDTO.getSecondDoctorId())
                    .orElseThrow(() -> new RuntimeException("Second Doctor ID: " + secondOpinionDTO.getSecondDoctorId() + " not found!")));
        }

        secondOpinion.setReason(secondOpinionDTO.getReason());
        secondOpinion.setSecondOpinionNotes(secondOpinionDTO.getSecondOpinionNotes());
        secondOpinion.setRequestedAt(secondOpinionDTO.getRequestedAt());
        secondOpinion.setRespondedAt(secondOpinionDTO.getRespondedAt());
        secondOpinion.setStatus(secondOpinionDTO.getStatus());

        return secondOpinion;
    }
}
