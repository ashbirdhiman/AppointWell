package com.appointwell.RestImpl;

import com.appointwell.POJO.Availability;
import com.appointwell.POJO.Doctor;
import com.appointwell.Rest.DoctorRest;
import com.appointwell.Services.DoctorService;
import com.appointwell.Utils.AppointWellConstants;
import com.appointwell.Utils.AppointWellUtils;
import com.appointwell.Wrapper.DTOTransformer;
import com.appointwell.Wrapper.DoctorWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorRestImpl implements DoctorRest {

    @Autowired
    private DoctorService doctorService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> requestMapping) {
        try {
            // Calling the doctor service to handle signup logic
            return doctorService.signup(requestMapping);
        } catch (Exception e) {
            log.error("Error during doctor signup", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMapping) {
        try {
            // Calling the doctor service to handle login logic
            return doctorService.login(requestMapping);
        } catch (Exception e) {
            log.error("Error during doctor login", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @Override
    @GetMapping("/getBySpecialization/{specialization}")
    public ResponseEntity<List<DoctorWrapper>> getDoctorsBySpecialization(@PathVariable String specialization) {
        try {
            return doctorService.getDoctorsBySpecialization(specialization);

        } catch (Exception e) {
            log.error("Error fetching doctors by specialization", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @GetMapping("/getAll")
    public ResponseEntity<List<DoctorWrapper>> getAllDoctors() {
        try {
            // Fetching all doctors from the service
            return doctorService.getAllDoctors();
        } catch (Exception e) {
            log.error("Error fetching all doctors", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @PostMapping("/update")
    public ResponseEntity<String> update(@RequestBody Map<String, String> requestMapping) {
        try {
            // Updating doctor information via the service
            return doctorService.update(requestMapping);
        } catch (Exception e) {
            log.error("Error updating doctor details", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMapping) {
        try {
            // Changing doctor password via the service
            return doctorService.changePassword(requestMapping);
        } catch (Exception e) {
            log.error("Error changing doctor password", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/getAvailableDoctors")
    public ResponseEntity<List<DoctorWrapper>> getAvailableDoctors(@RequestParam int doctorId) {
        try {
            // Fetching available doctors based on doctorId from the service
            return doctorService.getAvailableDoctors(doctorId);
        } catch (Exception e) {
            log.error("Error fetching available doctors", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    @PostMapping("/addAvailability")
    public ResponseEntity<List<DoctorWrapper>> addAvailabity(@RequestBody Availability availability) {
        try {
            // Adding availability for a doctor
            return doctorService.addAvailability(availability);
        } catch (Exception e) {
            log.error("Error adding availability for doctor", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
