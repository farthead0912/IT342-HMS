package edu.cit.hms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import edu.cit.hms.entity.DoctorEntity;
import edu.cit.hms.service.DoctorService;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @GetMapping("/all")
    public List<DoctorEntity> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    @GetMapping("/{id}")
    public DoctorEntity getDoctorById(@PathVariable int id) {
        return doctorService.getDoctorById(id);
    }

    @PostMapping("/add")
    public DoctorEntity createDoctor(@RequestBody DoctorEntity doctor) {
        return doctorService.createDoctor(doctor);
    }

    @PutMapping("/update/{id}")
    public DoctorEntity updateDoctor(@PathVariable int doctorId, @RequestBody DoctorEntity doctor) {
        return doctorService.updateDoctor(doctorId, doctor);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDoctor(@PathVariable int id) {
        return doctorService.deleteDoctor(id);
    }
}
