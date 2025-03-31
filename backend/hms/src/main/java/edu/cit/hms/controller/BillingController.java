package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/api/billing")
public class BillingController {
    @Autowired
    private BillingService billingService;

    @GetMapping("/")
    @ApiResponse(responseCode = "400", description = "Successfully retrieved billings")
    public List<BillingEntity> getBillings() {
        return billingService.getBillings();
    }

    @GetMapping("/{billingId}")
    @ApiResponse(responseCode = "400", description = "Successfully retrieved billing by ID")
    public BillingEntity getBillingById(@PathVariable int billingId) {
        return billingService.getBillingById(billingId);
    }

    @PostMapping("/")
    @ApiResponse(responseCode = "201", description = "Successfully created billing record")
    public BillingEntity createBilling(@RequestBody BillingEntity newBilling) {
        return billingService.createBilling(newBilling);
    }

    @PutMapping("/{billingId}")
    @ApiResponse(responseCode = "200", description = "Successfully updated billing record")
    public BillingEntity updateBilling(@PathVariable int billingId, @RequestBody BillingEntity billing) {
        return billingService.updateBilling(billingId, billing);
    }

    @DeleteMapping("/{billingId}")
    @ApiResponse(responseCode = "200", description = "Successfully deleted billing record")
    public String deleteBilling(@PathVariable int billingId) {
        return billingService.deleteBilling(billingId);
    }
}
