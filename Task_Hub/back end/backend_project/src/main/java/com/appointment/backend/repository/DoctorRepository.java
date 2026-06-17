 package com.appointment.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.backend.entity.Doctor;

public interface DoctorRepository
        extends JpaRepository<Doctor, Long> {

}