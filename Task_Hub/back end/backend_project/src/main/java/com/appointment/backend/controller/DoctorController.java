 package com.appointment.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.appointment.backend.entity.Doctor;
import com.appointment.backend.service.DoctorService;

@RestController
@RequestMapping("/doctors")
@CrossOrigin(origins = "http://localhost:5173")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // ADD DOCTOR
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // GET ALL DOCTORS
    @GetMapping("/all")
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // UPDATE DOCTOR
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public Doctor updateDoctor(
            @PathVariable Long id,
            @RequestBody Doctor doctor) {

        return doctorService.updateDoctor(id, doctor);
    }

    // DELETE DOCTOR
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteDoctor(@PathVariable Long id) {

        return doctorService.deleteDoctor(id);
    }
}