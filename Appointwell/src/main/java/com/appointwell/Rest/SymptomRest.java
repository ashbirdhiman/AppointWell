package com.appointwell.Rest;

import com.appointwell.POJO.Symptom;

import com.appointwell.Wrapper.SymptomWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/symptom")
public interface SymptomRest {

    // Register a new symptom
    @PostMapping(path = "/add")
    public ResponseEntity<String> addSymptom(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get all symptoms
    @GetMapping(path = "/getAll")
    public ResponseEntity<List<Symptom>> getAllSymptoms();

    // Get symptom by name
    @GetMapping(path = "/getByName")
    public ResponseEntity<Symptom> getSymptomByName(@RequestBody(required = true) Map<String, String> requestMapping);


    @GetMapping(path = "/symptom/getByPatientID/{patientID}")
    ResponseEntity<Symptom> getSymptomByPatientID(@PathVariable int patientID);
}
