package com.appointwell.Rest;

import com.appointwell.POJO.Appointment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/appointment")
public interface AppointmentRest {

    // Create a new appointment
    @PostMapping(path = "/book")
    public ResponseEntity<String> bookAppointment(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get all appointments for a patient
    @GetMapping(path = "/getByPatient/{patientID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(@PathVariable(required = true) int patientID);

    // Get all appointments for a doctor
    @GetMapping(path = "/getByDoctor/{doctorID}")
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(@PathVariable(required = true) int doctorID);

    // Get all appointments
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Appointment>> getAllAppointments();

    // Cancel an appointment
    @PostMapping(path = "/cancel")
    public ResponseEntity<String> cancelAppointment(@RequestBody(required = true) Map<String, String> requestMapping);
}
