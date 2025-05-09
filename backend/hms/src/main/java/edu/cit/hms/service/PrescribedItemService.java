package edu.cit.hms.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.cit.hms.dto.PrescribedItemDTO;
import edu.cit.hms.entity.PrescribedItem;
import edu.cit.hms.repository.PrescribedItemRepository;
import edu.cit.hms.repository.PrescriptionRepository;

@Service
public class PrescribedItemService {
    @Autowired
    private PrescribedItemRepository prescribedItemRepository;

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    public PrescribedItemDTO createPrescribedItem(PrescribedItemDTO prescribedItemDTO) {
        PrescribedItem prescribedItem = convertFromDTO(prescribedItemDTO);
        prescribedItem = prescribedItemRepository.save(prescribedItem);
        return convertToDTO(prescribedItem);
    }

    public List<PrescribedItemDTO> getAllPrescribedItems() {
        List<PrescribedItem> prescribedItems = prescribedItemRepository.findAll();
        return prescribedItems.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PrescribedItemDTO getPrescribedItemById(int itemId) {
        return prescribedItemRepository.findById(itemId)
                .map(this::convertToDTO)
                .orElseThrow(() -> new RuntimeException("Prescribed item with ID: " + itemId + " not found."));
    }

    public List<PrescribedItemDTO> getPrescribedItemByPrescriptionId(int prescriptionId) {
        List<PrescribedItem> prescribedItems = prescribedItemRepository.findByPrescription_PrescriptionId(prescriptionId);

        if (prescribedItems.isEmpty()) {
            throw new RuntimeException("No prescribed items found for Prescription ID: " + prescriptionId);
        }
        
        return prescribedItems.stream()
                .map(this::convertToDTO)
                .toList();
    }

    public PrescribedItemDTO updatePrescribedItem(int itemId, PrescribedItemDTO newPrescribedItemDTO) {
        PrescribedItem prescribedItem = prescribedItemRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Prescribed item with ID: " + itemId + " not found."));

        if (newPrescribedItemDTO.getPrescriptionId() > 0) {
            prescribedItem.setPrescription(prescriptionRepository.findById(newPrescribedItemDTO.getPrescriptionId())
                    .orElseThrow(() -> new RuntimeException("Prescription ID: " + newPrescribedItemDTO.getPrescriptionId() + " not found.")));
        }
        if (newPrescribedItemDTO.getMedicineName() != null) {
            prescribedItem.setMedicineName(newPrescribedItemDTO.getMedicineName());
        }
        if (newPrescribedItemDTO.getDosage() != null) {
            prescribedItem.setDosage(newPrescribedItemDTO.getDosage());
        }
        if (newPrescribedItemDTO.getFrequency() != null) {
            prescribedItem.setFrequency(newPrescribedItemDTO.getFrequency());
        }
        if (newPrescribedItemDTO.getDuration() != null) {
            prescribedItem.setDuration(newPrescribedItemDTO.getDuration());
        }

        return convertToDTO(prescribedItemRepository.save(prescribedItem));
    }

    public String deletePrescribedItem(int itemId) {
        if (prescribedItemRepository.existsById(itemId)) {
            prescribedItemRepository.deleteById(itemId);
            return "Prescribed item with ID: " + itemId + " deleted successfully.";
        } else {
            throw new RuntimeException("Prescribed item with ID: " + itemId + " not found.");
        }
    }

    public PrescribedItemDTO convertToDTO(PrescribedItem prescribedItem) {
        PrescribedItemDTO dto = new PrescribedItemDTO();

        dto.setItemId(prescribedItem.getItemId());
        dto.setPrescriptionId(prescribedItem.getPrescription().getPrescriptionId());
        dto.setMedicineName(prescribedItem.getMedicineName());
        dto.setDosage(prescribedItem.getDosage());
        dto.setFrequency(prescribedItem.getFrequency());
        dto.setDuration(prescribedItem.getDuration());

        return dto;
    }

    public PrescribedItem convertFromDTO(PrescribedItemDTO dto) {
        PrescribedItem prescribedItem = new PrescribedItem();

        prescribedItem.setItemId(dto.getItemId());
        prescribedItem.setPrescription(prescriptionRepository.findById(dto.getPrescriptionId())
                .orElseThrow(() -> new RuntimeException("Prescription ID: " + dto.getPrescriptionId() + " not found.")));
        prescribedItem.setMedicineName(dto.getMedicineName());
        prescribedItem.setDosage(dto.getDosage());
        prescribedItem.setFrequency(dto.getFrequency());
        prescribedItem.setDuration(dto.getDuration());

        return prescribedItem;
    }
}
