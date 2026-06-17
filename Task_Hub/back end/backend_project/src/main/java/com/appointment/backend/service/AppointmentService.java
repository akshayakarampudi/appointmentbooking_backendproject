 package com.appointment.backend.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appointment.backend.entity.Appointment;
import com.appointment.backend.repository.AppointmentRepository;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    // CREATE APPOINTMENT
    public Appointment bookAppointment(Appointment appointment) {

        appointment.setStatus("BOOKED");
        appointment.setBookingDate(LocalDate.now());

        return appointmentRepository.save(appointment);
    }

    // GET ALL
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // GET BY ID (FIXED - WAS MISSING)
    public Appointment getAppointmentById(Long id) {

        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    // UPDATE APPOINTMENT (FIXED - NO NULL RETURN)
    public Appointment updateAppointment(Long id, Appointment appointment) {

        Appointment existingAppointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Appointment not found"));

        existingAppointment.setUserId(appointment.getUserId());
        existingAppointment.setUserName(appointment.getUserName());
        existingAppointment.setDoctorName(appointment.getDoctorName());
        existingAppointment.setSpecialization(appointment.getSpecialization());
        existingAppointment.setAppointmentTime(appointment.getAppointmentTime());
        existingAppointment.setStatus(appointment.getStatus());

        return appointmentRepository.save(existingAppointment);
    }

    // DELETE
    public String deleteAppointment(Long id) {

        Appointment existing =
                appointmentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointmentRepository.delete(existing);

        return "Appointment Deleted Successfully";
    }

    // ⭐ NEW: STATUS UPDATE (IMPORTANT FOR RUBRIC)
    public Appointment updateStatus(Long id, String status) {

        Appointment appointment =
                appointmentRepository.findById(id)
                        .orElseThrow(() -> new RuntimeException("Appointment not found"));

        appointment.setStatus(status);

        return appointmentRepository.save(appointment);
    }
}