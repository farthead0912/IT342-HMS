package edu.cit.hms.controller;

import edu.cit.hms.dto.SecondOpinionDTO;
import edu.cit.hms.service.SecondOpinionService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/second-opinions", consumes = "application/json", produces = "application/json")
@CrossOrigin
@ApiResponses({
    @ApiResponse(responseCode = "200", description = "Request was successful"),
    @ApiResponse(responseCode = "400", description = "Bad request"),
    @ApiResponse(responseCode = "404", description = "Resource not found"),
    @ApiResponse(responseCode = "500", description = "Internal server error")
})
@PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR')")
public class SecondOpinionController {

    @Autowired
    private SecondOpinionService secondOpinionService;

    @PostMapping
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "201", description = "Second opinion created successfully"),
        @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<SecondOpinionDTO> createSecondOpinion(@RequestBody SecondOpinionDTO secondOpinionDTO) {
        SecondOpinionDTO createdSecondOpinion = secondOpinionService.createSecondOpinion(secondOpinionDTO);
        return ResponseEntity.ok(createdSecondOpinion);
    }

    @GetMapping
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched all second opinions successfully"),
        @ApiResponse(responseCode = "404", description = "No second opinions found")
    })
    public ResponseEntity<List<SecondOpinionDTO>> getAllSecondOpinions() {
        List<SecondOpinionDTO> secondOpinions = secondOpinionService.getAllSecondOpinions();
        return ResponseEntity.ok(secondOpinions);
    }

    @GetMapping(value = "/{secondOpinionId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched second opinion successfully"),
        @ApiResponse(responseCode = "404", description = "Second opinion not found")
    })
    public ResponseEntity<SecondOpinionDTO> getSecondOpinionById(@PathVariable int secondOpinionId) {
        SecondOpinionDTO secondOpinion = secondOpinionService.getSecondOpinionById(secondOpinionId);
        return ResponseEntity.ok(secondOpinion);
    }

    @GetMapping(value = "/patient/{patientId}")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Fetched second opinions for the patient successfully"),
        @ApiResponse(responseCode = "404", description = "No second opinions found for the given patient ID")
    })
    public ResponseEntity<List<SecondOpinionDTO>> getSecondOpinionsByPatientId(@PathVariable int patientId) {
        List<SecondOpinionDTO> secondOpinions = secondOpinionService.getSecondOpinionsByPatientId(patientId);
        return ResponseEntity.ok(secondOpinions);
    }

    @PutMapping(value = "/{secondOpinionId}")
    @PreAuthorize("hasRole('DOCTOR')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Second opinion updated successfully"),
        @ApiResponse(responseCode = "404", description = "Second opinion not found")
    })
    public ResponseEntity<SecondOpinionDTO> updateSecondOpinion(@PathVariable int secondOpinionId, @RequestBody SecondOpinionDTO secondOpinionDTO) {
        SecondOpinionDTO updatedSecondOpinion = secondOpinionService.updateSecondOpinion(secondOpinionId, secondOpinionDTO);
        return ResponseEntity.ok(updatedSecondOpinion);
    }

    @DeleteMapping(value = "/{secondOpinionId}")
    @PreAuthorize("hasRole('ADMIN')")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Second opinion deleted successfully"),
        @ApiResponse(responseCode = "404", description = "Second opinion not found")
    })
    public ResponseEntity<String> deleteSecondOpinion(@PathVariable int secondOpinionId) {
        String response = secondOpinionService.deleteSecondOpinion(secondOpinionId);
        return ResponseEntity.ok(response);
    }
}
