package edu.cit.hms.controller;

import edu.cit.hms.dto.MedicineOrderDTO;
import edu.cit.hms.service.MedicineOrderService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/medicine-orders", consumes = "application/json", produces = "application/json")
@CrossOrigin
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request was successful"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
public class MedicineOrderController {

    @Autowired
    private MedicineOrderService medicineOrderService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Medicine order created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<MedicineOrderDTO> createMedicineOrder(@RequestBody MedicineOrderDTO medicineOrderDTO) {
        MedicineOrderDTO createdOrder = medicineOrderService.createMedicineOrder(medicineOrderDTO);
        return ResponseEntity.ok(createdOrder);
    }

    @GetMapping
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched all medicine orders successfully"),
        @ApiResponse(responseCode = "404", description = "No medicine orders found")
    })
    public ResponseEntity<List<MedicineOrderDTO>> getAllMedicineOrders() {
        List<MedicineOrderDTO> orders = medicineOrderService.getAllMedicineOrders();
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "/{orderId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched medicine order successfully"),
        @ApiResponse(responseCode = "404", description = "Medicine order not found")
    })
    public ResponseEntity<MedicineOrderDTO> getMedicineOrderById(@PathVariable int orderId) {
        MedicineOrderDTO order = medicineOrderService.getMedicineOrderById(orderId);
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/patient/{patientId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched medicine orders for the patient successfully"),
        @ApiResponse(responseCode = "404", description = "No medicine orders found for the given patient ID")
    })
    public ResponseEntity<List<MedicineOrderDTO>> getMedicineOrdersByPatientId(@PathVariable int patientId) {
        List<MedicineOrderDTO> orders = medicineOrderService.getMedicineOrdersByPatientId(patientId);
        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "/prescription/{prescriptionId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched medicine orders for the prescription successfully"),
        @ApiResponse(responseCode = "404", description = "No medicine orders found for the given prescription ID")
    })
    public ResponseEntity<List<MedicineOrderDTO>> getMedicineOrdersByPrescriptionId(@PathVariable int prescriptionId) {
        List<MedicineOrderDTO> orders = medicineOrderService.getMedicineOrdersByPrescriptionId(prescriptionId);
        return ResponseEntity.ok(orders);
    }

    @PutMapping(value = "/{orderId}")
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Medicine order updated successfully"),
        @ApiResponse(responseCode = "404", description = "Medicine order not found")
    })
    public ResponseEntity<MedicineOrderDTO> updateMedicineOrder(@PathVariable int orderId, @RequestBody MedicineOrderDTO medicineOrderDTO) {
        MedicineOrderDTO updatedOrder = medicineOrderService.updateMedicineOrder(orderId, medicineOrderDTO);
        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping(value = "/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Medicine order deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Medicine order not found")
    })
    public ResponseEntity<String> deleteMedicineOrder(@PathVariable int orderId) {
        String response = medicineOrderService.deleteMedicineOrder(orderId);
        return ResponseEntity.ok(response);
    }
}