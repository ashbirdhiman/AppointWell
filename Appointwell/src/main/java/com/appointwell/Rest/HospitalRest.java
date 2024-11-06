package com.appointwell.Rest;

import com.appointwell.POJO.Hospital;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/hospital")
public interface HospitalRest {

    // Register a new hospital
    @PostMapping(path = "/add")
    public ResponseEntity<String> addHospital(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get all hospitals
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Hospital>> getAllHospitals();

    // Get hospital by name
    @GetMapping(path = "/getByName")
    public ResponseEntity<Hospital> getHospitalByName(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get hospitals by location
    @GetMapping(path = "/getByLocation")
    public ResponseEntity<List<Hospital>> getHospitalsByLocation(@RequestBody(required = true) Map<String, String> requestMapping);


    // GET mapping to get doctors by hospital
    @GetMapping("/getDoctorsByHospital/{hospitalID}")
    ResponseEntity<List<DoctorWrapper>> getDoctorsByHospital(@PathVariable int hospitalID);
}
