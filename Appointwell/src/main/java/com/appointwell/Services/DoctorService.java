package com.appointwell.Services;

import com.appointwell.POJO.Availability;
import com.appointwell.POJO.Doctor;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface DoctorService {

    ResponseEntity<String> signup(Map<String, String> requestMapping);

    ResponseEntity<String> login(Map<String, String> requestMapping);

    ResponseEntity<List<DoctorWrapper>> getDoctorsBySpecialization(String requestMapping);

    ResponseEntity<List<DoctorWrapper>> getAllDoctors();

    ResponseEntity<String> update(Map<String, String> requestMapping);

    ResponseEntity<String> changePassword(Map<String, String> requestMapping);

    ResponseEntity<List<DoctorWrapper>> getAvailableDoctors(int doctorId);

    ResponseEntity<List<DoctorWrapper>> addAvailability(Availability availability);
}
