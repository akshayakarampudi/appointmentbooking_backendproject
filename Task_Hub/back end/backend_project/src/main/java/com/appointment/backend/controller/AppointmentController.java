 package com.appointment.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.appointment.backend.entity.Appointment;
import com.appointment.backend.service.AppointmentService;

@RestController
@RequestMapping("/appointments")
@CrossOrigin(origins = "http://localhost:5173")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // BOOK APPOINTMENT
    @PostMapping("/book")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Appointment bookAppointment(
            @RequestBody Appointment appointment) {

        return appointmentService.bookAppointment(appointment);
    }

    // ADMIN + DOCTOR - GET ALL APPOINTMENTS
    @GetMapping("/all")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public List<Appointment> getAllAppointments() {

        return appointmentService.getAllAppointments();
    }

    // USER + ADMIN + DOCTOR APPOINTMENTS
    @GetMapping("/user")
    @PreAuthorize("hasAnyRole('USER','ADMIN','DOCTOR')")
    public List<Appointment> getUserAppointments() {

        return appointmentService.getAllAppointments();
    }

    // GET APPOINTMENT BY ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('USER','ADMIN','DOCTOR')")
    public Appointment getAppointmentById(
            @PathVariable Long id) {

        return appointmentService.getAppointmentById(id);
    }

    // UPDATE APPOINTMENT
    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public Appointment updateAppointment(
            @PathVariable Long id,
            @RequestBody Appointment appointment) {

        return appointmentService.updateAppointment(
                id,
                appointment
        );
    }

    // DELETE APPOINTMENT
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public String deleteAppointment(
            @PathVariable Long id) {

        appointmentService.deleteAppointment(id);

        return "Appointment Deleted Successfully";
    }

    // UPDATE STATUS
    @PutMapping("/status/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','DOCTOR')")
    public Appointment updateStatus(
            @PathVariable Long id,
            @RequestParam String status) {

        return appointmentService.updateStatus(
                id,
                status
        );
    }
}