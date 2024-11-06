package com.appointwell.Rest;

import com.appointwell.POJO.Hospital;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/admin")
public interface AdminRest {

    // Admin login endpoint
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMapping);

    // Add a new hospital
    @PostMapping(path = "/addHospital")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital);

    // View all hospitals
    @GetMapping(path = "/viewHospitals")
    public ResponseEntity<List<Hospital>> viewHospitals();

    // Assign doctor to hospital
    @PostMapping(path = "/assignDoctor")
    public ResponseEntity<String> assignDoctorToHospital(
            @RequestParam int doctorId,
            @RequestParam int hospitalId
    );
}
