package com.appointwell.RestImpl;

import com.appointwell.POJO.Patient;
import com.appointwell.Rest.PatientRest;
import com.appointwell.Services.PatientService;
import com.appointwell.Utils.AppointWellConstants;
import com.appointwell.Utils.AppointWellUtils;
import com.appointwell.Wrapper.PatientWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/patient")
@Slf4j
public class PatientRestImpl implements PatientRest {

    @Autowired
    private PatientService patientService;

    @Override
    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody Map<String, String> requestMapping) {
        log.info("Received signup request: {}", requestMapping);
        try {
            return patientService.signUp(requestMapping);
        } catch (Exception e) {
            log.error("Error during signup: ", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Map<String, String> requestMapping) {
        log.info("Received login request: {}", requestMapping);
        try {
            return patientService.login(requestMapping);
        } catch (Exception e) {
            log.error("Error during login: ", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/all")
    public ResponseEntity<List<PatientWrapper>> getAllPatients() {
        log.info("Fetching all patients");
        try {
            return patientService.getAllPatients();
        } catch (Exception e) {
            log.error("Error fetching all patients: ", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/email/{email}")
    public ResponseEntity<Patient> getPatientByEmail(@PathVariable("email") String email) {
        log.info("Fetching patient by email: {}", email);
        try {
            return patientService.getPatientByEmail(email);
        } catch (Exception e) {
            log.error("Error fetching patient by email: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @GetMapping("/id/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable("id") int id) {
        log.info("Fetching patient by id: {}", id);
        try {
            return patientService.getPatientById(id);
        } catch (Exception e) {
            log.error("Error fetching patient by id: ", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/changePassword")
    public ResponseEntity<String> changePassword(@RequestBody Map<String, String> requestMapping) {
        log.info("Received password change request: {}", requestMapping);
        try {
            return patientService.changePassword(requestMapping);
        } catch (Exception e) {
            log.error("Error changing password: ", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
