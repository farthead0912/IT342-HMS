package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.BillingEntity;
import edu.cit.hms.service.BillingService;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@ApiResponses(value = {
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Billing not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@RestController
@RequestMapping(value = "/api/billing", produces = "application/json")
@PreAuthorize("hasAnyRole('ADMIN', 'STAFF')") // Class-level restriction
public class BillingController {
    @Autowired
    private BillingService billingService;

    // Gets all billing records
    @GetMapping(value = "/")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved billings")
    public ResponseEntity<List<BillingEntity>> getBillings() {
        return ResponseEntity.ok(billingService.getBillings());
    }

    // Gets billing record by ID
    @GetMapping(value = "/{billingId}")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved billing by ID")
    public ResponseEntity<BillingEntity> getBillingById(@PathVariable int billingId) {
        return ResponseEntity.ok(billingService.getBillingById(billingId));
    }

    // Adds new billing record
    @PostMapping(value = "/", consumes = "application/json")
    @ApiResponse(responseCode = "201", description = "Successfully created billing record")
    public ResponseEntity<BillingEntity> createBilling(@RequestBody BillingEntity newBilling) {
        return ResponseEntity.status(201).body(billingService.createBilling(newBilling));
    }

    // Updates billing record details by ID
    @PutMapping(value = "/{billingId}", consumes = "application/json")
    @ApiResponse(responseCode = "200", description = "Successfully updated billing record")
    public ResponseEntity<BillingEntity> updateBilling(@PathVariable int billingId, @RequestBody BillingEntity billing) {
        return ResponseEntity.ok(billingService.updateBilling(billingId, billing));
    }

    // Deletes billing record by ID
    @PreAuthorize("hasRole('ADMIN')") // Only ADMIN can delete billing records
    @DeleteMapping(value = "/{billingId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted billing record")
    public ResponseEntity<String> deleteBilling(@PathVariable int billingId) {
        return ResponseEntity.ok(billingService.deleteBilling(billingId));
    }
}
