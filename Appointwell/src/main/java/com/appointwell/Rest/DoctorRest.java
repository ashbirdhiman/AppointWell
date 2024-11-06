package com.appointwell.Rest;


import com.appointwell.POJO.Availability;
import com.appointwell.POJO.Doctor;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.Map;

@RequestMapping(path = "/doctor")
public interface DoctorRest {

    // Register a new doctor
    @PostMapping(path = "/signup")
    public ResponseEntity<String> signup(@RequestBody(required = true) Map<String, String> requestMapping);

    // Login doctor
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get all doctors by specialization

    @GetMapping("/getBySpecialization/{specialization}")
    ResponseEntity<List<DoctorWrapper>> getDoctorsBySpecialization(@PathVariable String specialization);

    // Get all doctors
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<DoctorWrapper>> getAllDoctors();

    // Update doctor details
    @PostMapping(path = "/update")
    public ResponseEntity<String> update(@RequestBody(required = true) Map<String, String> requestMapping);

    // Change doctor password
    @PostMapping(path = "/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get available doctors
    @GetMapping(path = "/getAvailableDoctor")
    public ResponseEntity<List<DoctorWrapper>> getAvailableDoctors(@PathVariable int doctorId);


    @PostMapping("/addAvailability")
    ResponseEntity<List<DoctorWrapper>> addAvailabity(@RequestBody Availability availability);
}
