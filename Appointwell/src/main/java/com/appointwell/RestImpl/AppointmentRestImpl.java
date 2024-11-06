package com.appointwell.RestImpl;

import com.appointwell.POJO.Appointment;
import com.appointwell.Rest.AppointmentRest;

import com.appointwell.Services.AppointmentService;

import com.appointwell.Utils.AppointWellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/appointment")
public class AppointmentRestImpl implements AppointmentRest {

    @Autowired
    private AppointmentService appointmentService;

    // Book a new appointment
    @Override
    @PostMapping(path = "/book")
    public ResponseEntity<String> bookAppointment(@RequestBody Map<String, String> requestMapping) {
        try {
            return appointmentService.bookAppointment(requestMapping);
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Failed to book the appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get all appointments for a patient
    @Override
    @GetMapping(path = "/getByPatient/{patientID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable int patientID) {
        try {
            return appointmentService.getAppointmentsByPatient(patientID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get all appointments for a doctor
    @Override
    @GetMapping(path = "/getByDoctor/{doctorID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable  int doctorID) {
        try {
            return appointmentService.getAppointmentsByDoctor(doctorID);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Get all appointments
    @Override
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            return appointmentService.getAllAppointments();
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // Cancel an appointment
    @Override
    @PostMapping(path = "/cancel")
    public ResponseEntity<String> cancelAppointment(@RequestBody Map<String, String> requestMapping) {
        try {
            return appointmentService.cancelAppointment(requestMapping);
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Failed to cancel the appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
