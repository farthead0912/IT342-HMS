package edu.cit.hms.controller;

import edu.cit.hms.dto.PrescribedItemDTO;
import edu.cit.hms.service.PrescribedItemService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

@RestController
@RequestMapping(value = "/api/prescribed-items", consumes = "application/json", produces = "application/json")
@CrossOrigin
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request was successful"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
public class PrescribedItemController {

    @Autowired
    private PrescribedItemService prescribedItemService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Prescribed item created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<PrescribedItemDTO> createPrescribedItem(@RequestBody PrescribedItemDTO prescribedItemDTO) {
        PrescribedItemDTO createdItem = prescribedItemService.createPrescribedItem(prescribedItemDTO);
        return ResponseEntity.ok(createdItem);
    }

    @GetMapping
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched all prescribed items successfully"),
        @ApiResponse(responseCode = "404", description = "No prescribed items found")
    })
    public ResponseEntity<List<PrescribedItemDTO>> getAllPrescribedItems() {
        List<PrescribedItemDTO> items = prescribedItemService.getAllPrescribedItems();
        return ResponseEntity.ok(items);
    }

    @GetMapping(value = "/{itemId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched prescribed item successfully"),
        @ApiResponse(responseCode = "404", description = "Prescribed item not found")
    })
    public ResponseEntity<PrescribedItemDTO> getPrescribedItemById(@PathVariable int itemId) {
        PrescribedItemDTO item = prescribedItemService.getPrescribedItemById(itemId);
        return ResponseEntity.ok(item);
    }

    @GetMapping(value = "/prescription/{prescriptionId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched prescribed items for the prescription successfully"),
        @ApiResponse(responseCode = "404", description = "No prescribed items found for the given prescription ID")
    })
    public ResponseEntity<List<PrescribedItemDTO>> getPrescribedItemsByPrescriptionId(@PathVariable int prescriptionId) {
        List<PrescribedItemDTO> items = prescribedItemService.getPrescribedItemByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(items);
    }

    @PutMapping(value = "/{itemId}")
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prescribed item updated successfully"),
        @ApiResponse(responseCode = "404", description = "Prescribed item not found")
    })
    public ResponseEntity<PrescribedItemDTO> updatePrescribedItem(@PathVariable int itemId, @RequestBody PrescribedItemDTO prescribedItemDTO) {
        PrescribedItemDTO updatedItem = prescribedItemService.updatePrescribedItem(itemId, prescribedItemDTO);
        return ResponseEntity.ok(updatedItem);
    }

    @DeleteMapping(value = "/{itemId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Prescribed item deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Prescribed item not found")
    })
    public ResponseEntity<String> deletePrescribedItem(@PathVariable int itemId) {
        String response = prescribedItemService.deletePrescribedItem(itemId);
        return ResponseEntity.ok(response);
    }
}