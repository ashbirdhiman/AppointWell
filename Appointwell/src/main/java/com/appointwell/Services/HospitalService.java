package com.appointwell.Services;

import com.appointwell.POJO.Hospital;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Map;


public interface HospitalService {

    // Add a new hospital
    ResponseEntity<String> addHospital(Map<String, String> hospitalDetails);

    // Get all hospitals
    ResponseEntity<List<Hospital>> getAllHospitals();

    // Get hospital by name
    ResponseEntity<Hospital> getHospitalByName(Map<String, String> hospitalDetails);

    // Get hospitals by location
   
    // Get list of doctors for a given hospital
    ResponseEntity<List<DoctorWrapper>> getDoctorsByHospital(@PathVariable int hospitalID);


    ResponseEntity<List<Hospital>> getHospitalsByLocation(Map<String, String> requestMapping);
}
