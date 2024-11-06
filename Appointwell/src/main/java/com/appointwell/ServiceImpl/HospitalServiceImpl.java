package com.appointwell.ServiceImpl;

import com.appointwell.DAO.HospitalDAO;
import com.appointwell.POJO.Doctor;
import com.appointwell.POJO.Hospital;
import com.appointwell.Services.HospitalService;
import com.appointwell.Utils.AppointWellUtils;
import com.appointwell.Wrapper.DTOTransformer;
import com.appointwell.Wrapper.DoctorWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class HospitalServiceImpl implements HospitalService {

    @Autowired
    private HospitalDAO hospitalDao;

    @Override
    public ResponseEntity<String> addHospital(Map<String, String> hospitalDetails) {
        log.info("Inside Add Hospital Method :{}", hospitalDetails);
        try {
            if (validHospitalRequestMap(hospitalDetails)) {
                // Check if the hospital with the same name already exists
                Hospital existingHospital = hospitalDao.findByHospitalName(hospitalDetails.get("name"));
                if (existingHospital == null) {
                    // Create and save the new hospital
                    Hospital hospital = getHospitalFromMap(hospitalDetails);
                    hospitalDao.save(hospital);
                    return AppointWellUtils.getResponseEntity("Hospital added successfully", HttpStatus.OK);
                } else {
                    return AppointWellUtils.getResponseEntity("Hospital with this name already exists", HttpStatus.BAD_REQUEST);
                }
            }
        } catch (Exception e) {
            log.error("Error while adding hospital", e);
            return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
        }
        return AppointWellUtils.getResponseEntity("Invalid input data", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<List<Hospital>> getAllHospitals() {
        try {
            List<Hospital> hospitals = hospitalDao.findAll();
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching hospitals", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Hospital> getHospitalByName(Map<String, String> hospitalDetails) {
        try {
            String hospitalName = hospitalDetails.get("name");
            Hospital hospital = hospitalDao.findByHospitalName(hospitalName);
            if (hospital != null) {
                return new ResponseEntity<>(hospital, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            log.error("Error while fetching hospital by name", e);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Hospital>> getHospitalsByLocation(Map<String, String> hospitalDetails) {
        try {
            String longitude = hospitalDetails.get("longitude");
            String latitude= hospitalDetails.get("latitude");
            List<Hospital> hospitals = hospitalDao.findHospitalByLongitudeandLatitude(longitude,latitude);
            return new ResponseEntity<>(hospitals, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching hospitals by location", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DoctorWrapper>> getDoctorsByHospital(@PathVariable int hospitalID) {
        try {

            List<Doctor> doctors = hospitalDao.findDoctorsByHospitalId(hospitalID);

            // Map each Doctor to a DoctorDTO
            List<DoctorWrapper> doctorDTOs = doctors.stream()
                    .map(DTOTransformer::toDoctorDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
//            return new ResponseEntity<>(doctors, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error while fetching doctors by hospital", e);
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validHospitalRequestMap(Map<String, String> hospitalDetails) {
        return hospitalDetails.containsKey("name") && hospitalDetails.containsKey("address")
                && hospitalDetails.containsKey("latitude") && hospitalDetails.containsKey("longitude");
    }

    private Hospital getHospitalFromMap(Map<String, String> hospitalDetails) {
        Hospital hospital = new Hospital();
        hospital.setName(hospitalDetails.get("name"));
        hospital.setAddress(hospitalDetails.get("address"));
        hospital.setLatitude(Double.parseDouble(hospitalDetails.get("latitude")));
        hospital.setLongitude(Double.parseDouble(hospitalDetails.get("longitude")));
        return hospital;
    }
}
