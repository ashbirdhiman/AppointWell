package com.appointwell.Services;

import com.appointwell.POJO.Patient;
import com.appointwell.Wrapper.PatientWrapper;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface PatientService {

    ResponseEntity<String> signUp(Map<String, String> requestMapping);

    ResponseEntity<String> login(Map<String, String> requestMapping);

    ResponseEntity<List<PatientWrapper>> getAllPatients();

    ResponseEntity<String> update(Map<String, String> requestMapping);

    ResponseEntity<String> changePassword(Map<String, String> requestMapping);

    ResponseEntity<Patient> getPatientById(int id);

    ResponseEntity<Patient> getPatientByEmail(String email);
}
