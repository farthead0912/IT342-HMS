package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.PatientEntity;
import edu.cit.hms.service.PatientService;

@RestController
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @GetMapping("/all")
    public List<PatientEntity> getPatients() {
        return patientService.getPatients();
    }

    @GetMapping("/{id}")
    public PatientEntity getPatientById(@PathVariable int id) {
        return patientService.getPatientById(id);
    }

    @PostMapping("/add")
    public PatientEntity createPatient(@RequestBody PatientEntity patient) {
        return patientService.createPatient(patient);
    }

    @PutMapping("/update/{id}")
    public PatientEntity updatePatient(@PathVariable int patientId, @RequestBody PatientEntity patient) {
        return patientService.updatePatient(patientId, patient);
    }

    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable int id) {
        return patientService.deletePatient(id);
    }
}
