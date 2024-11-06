package com.appointwell.ServiceImpl;

import com.appointwell.DAO.PatientDAO;
import com.appointwell.POJO.Patient;
import com.appointwell.Services.PatientService;
import com.appointwell.Utils.AppointWellUtils;
import com.appointwell.Wrapper.PatientWrapper;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Slf4j
@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientDAO patientDao;

    @Override
    public ResponseEntity<String> signUp(Map<String, String> requestMapping) {
        log.info("Inside Patient Signup Method :{}", requestMapping);
        try {
            if (validRequestMap(requestMapping)) {
                Patient patient = patientDao.findPatientByEmail(requestMapping.get("email"));
                if (Objects.isNull(patient)) {
                    patientDao.save(getPatientFromMap(requestMapping));
                    return AppointWellUtils.getResponseEntity("Sign Up Successful", HttpStatus.OK);
                } else {
                    return AppointWellUtils.getResponseEntity("Email Already Exists", HttpStatus.BAD_REQUEST);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<String> login(Map<String, String> requestMapping) {
        log.info("Inside Patient Login Method :{}", requestMapping);
        try {
            Patient patient = patientDao.findPatientByEmail(requestMapping.get("email"));
            if (patient != null && patient.getPassword().equals(requestMapping.get("password"))) {
                return new ResponseEntity<String>("{\"message\":\"Login Successful\"}", HttpStatus.OK);
            } else {
                return new ResponseEntity<String>("{\"message\":\"Invalid credentials\"}", HttpStatus.UNAUTHORIZED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<PatientWrapper>> getAllPatients() {
        try {
            return new ResponseEntity<>(patientDao.getAllPatients(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMapping) {
        return null;
    }

    @Override
    public ResponseEntity<Patient> getPatientByEmail(String email) {
        try {
            Optional<Patient> patient = Optional.ofNullable(patientDao.findPatientByEmail(email));
            if (patient.isPresent()) {
                return new ResponseEntity<>(patient.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<Patient> getPatientById(int id) {
        try {
            Optional<Patient> patient = patientDao.findById(id);
            if (patient.isPresent()) {
                return new ResponseEntity<>(patient.get(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }



    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMapping) {
        try {
            Patient patient = patientDao.findPatientByEmail(requestMapping.get("email"));
            if (patient != null) {
                if (patient.getPassword().equals(requestMapping.get("oldPassword"))) {
                    if (!requestMapping.get("oldPassword").equals(requestMapping.get("newPassword"))) {
                        patient.setPassword(requestMapping.get("newPassword"));
                        patientDao.save(patient);
                        return AppointWellUtils.getResponseEntity("Password updated successfully", HttpStatus.OK);
                    } else {
                        return AppointWellUtils.getResponseEntity("New password can't be same as old password", HttpStatus.BAD_REQUEST);
                    }
                } else {
                    return AppointWellUtils.getResponseEntity("Incorrect current password", HttpStatus.BAD_REQUEST);
                }
            } else {
                return AppointWellUtils.getResponseEntity("Patient not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validRequestMap(Map<String, String> requestMapping) {
        return requestMapping.containsKey("name") && requestMapping.containsKey("email")
                && requestMapping.containsKey("contact_number") && requestMapping.containsKey("password");
    }

    private Patient getPatientFromMap(Map<String, String> requestMap) {
        Patient patient = new Patient();
        patient.setName(requestMap.get("name"));
        patient.setEmail(requestMap.get("email"));
        patient.setPassword(requestMap.get("password"));
        patient.setContactNumber(requestMap.get("contact_number"));
        return patient;
    }
}
