 package com.appointment.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.backend.entity.Doctor;
import com.appointment.backend.repository.DoctorRepository;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    // CREATE
    public Doctor addDoctor(Doctor doctor) {
        return doctorRepository.save(doctor);
    }

    // READ ALL
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // READ BY ID
    public Doctor getDoctorById(Long id) {

        return doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor Not Found"));
    }

    // UPDATE
    public Doctor updateDoctor(Long id, Doctor doctor) {

        Doctor existing = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor Not Found"));

        existing.setDoctorName(
                doctor.getDoctorName()
        );

        existing.setSpecialization(
                doctor.getSpecialization()
        );

        existing.setAvailableTime(
                doctor.getAvailableTime()
        );

        return doctorRepository.save(existing);
    }

    // DELETE
    public String deleteDoctor(Long id) {

        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Doctor Not Found"));

        doctorRepository.delete(doctor);

        return "Doctor Deleted Successfully";
    }
}