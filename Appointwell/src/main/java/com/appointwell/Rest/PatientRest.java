package com.appointwell.Rest;

import com.appointwell.POJO.Patient;
import com.appointwell.Wrapper.PatientWrapper;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/patient")
public interface PatientRest {

    // Endpoint for signing up a patient
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> requestMapping);

    // Endpoint for patient login
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMapping);

    // Endpoint for getting all patients
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<PatientWrapper>> getAllPatients();

    // Endpoint for getting a patient by their email
    @GetMapping(path = "/getByEmail/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable("email") String email);

    // Endpoint for getting patient details by ID
    @GetMapping(path = "/getById/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id);

    // Endpoint for changing the patient password
    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMapping);
}
