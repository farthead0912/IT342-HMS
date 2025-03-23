package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.PatientRecordEntity;
import edu.cit.hms.service.PatientRecordService;

@RestController
@RequestMapping("/api/patient_record")
public class PatientRecordController {
    @Autowired
    private PatientRecordService patientRecordService;

    @GetMapping("/all")
    public List<PatientRecordEntity> getPatientRecords() {
        return patientRecordService.getPatientRecords();
    }

    @GetMapping("/{id}")
    public PatientRecordEntity getPatientRecordById(@PathVariable int id) {
        return patientRecordService.getPatientRecordById(id);
    }

    @PostMapping("/add")
    public PatientRecordEntity createPatientRecord(@RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.createPatientRecord(patientRecord);
    }

    @PutMapping("/update/{id}")
    public PatientRecordEntity updatePatientRecord(@PathVariable int patientRecordId, @RequestBody PatientRecordEntity patientRecord) {
        return patientRecordService.updatePatientRecord(patientRecordId, patientRecord);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePatientRecord(@PathVariable int id) {
        patientRecordService.deletePatientRecord(id);
    }
}
