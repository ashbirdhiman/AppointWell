package com.appointwell.ServiceImpl;

import com.appointwell.DAO.SymptomDAO;
import com.appointwell.POJO.Symptom;
import com.appointwell.Services.SymptomService;
import com.appointwell.Utils.AppointWellUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class SymptomServiceImpl implements SymptomService {

    @Autowired
    private SymptomDAO symptomDAO;

    @Override
    public ResponseEntity<String> addSymptom(Map<String, String> requestMapping) {
        log.info("Inside Add Symptom Method :{}", requestMapping);
        try {
            if (validRequestMap(requestMapping)) {
                String name = requestMapping.get("name");

                // Check if the symptom already exists
                Symptom existingSymptom = symptomDAO.findBySymptomName(name);
                if (Objects.isNull(existingSymptom)) {
                    Symptom symptom = new Symptom();
                    symptom.setName(name);
                    symptom.setSeverity(requestMapping.get("severity"));
                    symptom.setPatientID(2);

                    symptomDAO.save(symptom);
                    return AppointWellUtils.getResponseEntity("Symptom added successfully", HttpStatus.OK);
                } else {
                    return AppointWellUtils.getResponseEntity("Symptom already exists", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Symptom>> getAllSymptoms() {
        log.info("Inside Get All Symptoms Method");
        try {
            List<Symptom> symptoms = symptomDAO.findAll();

            return new ResponseEntity<>(symptoms, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Symptom> getSymptomByName(Map<String, String> requestMapping) {
        log.info("Inside Get Symptom By Name Method :{}", requestMapping);
        try {
            String name = requestMapping.get("name");
            Symptom symptom = symptomDAO.findBySymptomName(name);
                return new ResponseEntity<>(symptom, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Symptom> findBySymptomByPatientID(int id) {
//        log.info("Inside Get Symptom By Name Method :{}", requestMapping);
        try {
            Symptom symptom = symptomDAO.findBySymptomByPatientID(id);
            return new ResponseEntity<>(symptom, HttpStatus.OK);

        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    private boolean validRequestMap(Map<String, String> requestMapping) {
        return requestMapping.containsKey("name") && requestMapping.containsKey("severity");
    }
}
