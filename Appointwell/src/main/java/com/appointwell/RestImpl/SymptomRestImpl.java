package com.appointwell.RestImpl;

import com.appointwell.POJO.Symptom;
import com.appointwell.Rest.SymptomRest;
import com.appointwell.Services.SymptomService;
import com.appointwell.Utils.AppointWellConstants;
import com.appointwell.Utils.AppointWellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@Slf4j
public class SymptomRestImpl implements SymptomRest {

    @Autowired
    private SymptomService symptomService;

    // POST mapping to add a new symptom
    @PostMapping(path = "/symptom/add")
    @Override
    public ResponseEntity<String> addSymptom(@RequestBody Map<String, String> requestMapping) {
        try {
            // Attempt to add the symptom using the service layer
            return symptomService.addSymptom(requestMapping);
        } catch (Exception e) {
            log.error("Error adding symptom", e);
            // Return a response with an error message and a 500 INTERNAL_SERVER_ERROR status code
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET mapping to get all symptoms
    @GetMapping(path = "/symptom/getAll")
    @Override
    public ResponseEntity<List<Symptom>> getAllSymptoms() {
        log.info("Fetching all symptoms");
        try {
            // Fetch all symptoms from the service layer
            return symptomService.getAllSymptoms();
        } catch (Exception e) {
            log.error("Error fetching symptoms", e);
            // Return an empty list with a BAD_REQUEST status code in case of failure
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET mapping to get a symptom by its name
    @GetMapping(path = "/symptom/getByName")
    @Override
    public ResponseEntity<Symptom> getSymptomByName(@RequestBody Map<String, String> requestMapping) {
        try {
            // Fetch symptom by name using the service layer
            return symptomService.getSymptomByName(requestMapping);

        } catch (Exception e) {
            log.error("Error fetching symptom by name", e);
            // Return a BAD_REQUEST status code if an error occurs
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @GetMapping(path = "/symptom/getByPatientID/{patientID}")
    @Override
    public ResponseEntity<Symptom> getSymptomByPatientID(@RequestParam int patientID) {
        try {
            // Fetch symptom by name using the service layer
            return symptomService.findBySymptomByPatientID(patientID);

        } catch (Exception e) {
            log.error("Error fetching symptom by name", e);
            // Return a BAD_REQUEST status code if an error occurs
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
