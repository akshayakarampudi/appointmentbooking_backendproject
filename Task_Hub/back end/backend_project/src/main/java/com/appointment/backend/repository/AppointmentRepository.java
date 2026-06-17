 package com.appointment.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appointment.backend.entity.Appointment;

public interface AppointmentRepository
extends JpaRepository<Appointment, Long> {

}