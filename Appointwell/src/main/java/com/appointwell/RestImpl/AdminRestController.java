package com.appointwell.RestImpl;

import com.appointwell.POJO.Hospital;
import com.appointwell.Rest.AdminRest;
import com.appointwell.Services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/admin")
@RestController
public class AdminRestController implements AdminRest {

    @Autowired
    private AdminService adminService;

    // Admin login endpoint
    @PostMapping(path = "/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMapping) {
        // Extract email and password from request
        String email = requestMapping.get("email");
        String password = requestMapping.get("password");

        // Call the service method for login
        String response = adminService.adminLogin(email, password);

        // Return response as JSON
        return ResponseEntity.ok(response);
    }


    // Add a new hospital
    @PostMapping(path = "/addHospital")
    public ResponseEntity<String> addHospital(@RequestBody Hospital hospital) {
        String response = adminService.addHospital(hospital);
        return ResponseEntity.ok(response);
    }

    // View all hospitals
    @GetMapping(path = "/viewHospitals")
    public ResponseEntity<List<Hospital>> viewHospitals() {
        List<Hospital> hospitals = adminService.getHospitals();
        return ResponseEntity.ok(hospitals);
    }

    // Assign doctor to hospital
    @PostMapping(path = "/assignDoctor")
    public ResponseEntity<String> assignDoctorToHospital(
            @RequestParam int doctorId,
            @RequestParam int hospitalId
    ) {
        String response = adminService.assignDoctorToHospital(doctorId, hospitalId);
        return ResponseEntity.ok(response);
    }
}
