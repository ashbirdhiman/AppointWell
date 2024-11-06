package com.appointwell.RestImpl;

import com.appointwell.POJO.Hospital;
import com.appointwell.Rest.HospitalRest;
import com.appointwell.Services.HospitalService;
import com.appointwell.Wrapper.DoctorWrapper;
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
@RequestMapping("/hospitals")
@Slf4j
public class HospitalRestImpl implements HospitalRest {

    @Autowired
    private HospitalService hospitalService;

    // POST mapping to add a hospital
    @PostMapping("/addHospital")
    @Override
    public ResponseEntity<String> addHospital(@RequestBody Map<String, String> requestMapping) {
        try {
            return hospitalService.addHospital(requestMapping);
        } catch (Exception e) {
            log.error("Error adding hospital", e);
            return AppointWellUtils.getResponseEntity(AppointWellConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // GET mapping to get all hospitals
    @GetMapping("/getAllHospitals")
    @Override
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        log.info("Fetching all hospitals");
        try {
            return hospitalService.getAllHospitals();
        } catch (Exception e) {
            log.error("Error fetching hospitals", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET mapping to get a hospital by its name
    @GetMapping("/getHospitalByName")
    @Override
    public ResponseEntity<Hospital> getHospitalByName(@RequestParam Map<String, String> requestMapping) {
        try {
            return hospitalService.getHospitalByName(requestMapping);
        } catch (Exception e) {
            log.error("Error fetching hospital by name", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    // GET mapping to get hospitals by their location
    @GetMapping("/getHospitalsByLocation")
    @Override
    public ResponseEntity<List<Hospital>> getHospitalsByLocation(@RequestParam Map<String, String> requestMapping) {
        try {
            return hospitalService.getHospitalsByLocation(requestMapping);
        } catch (Exception e) {
            log.error("Error fetching hospitals by location", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    // GET mapping to get doctors by hospital
    @GetMapping("/getDoctorsByHospital/{hospitalID}")
    @Override
    public ResponseEntity<List<DoctorWrapper>> getDoctorsByHospital(@PathVariable int hospitalID) {
        try {
            return hospitalService.getDoctorsByHospital(hospitalID);
        } catch (Exception e) {
            log.error("Error fetching doctors by hospital", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }
}
