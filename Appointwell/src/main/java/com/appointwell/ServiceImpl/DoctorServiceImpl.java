package com.appointwell.ServiceImpl;

import com.appointwell.DAO.AvailabilityDAO;
import com.appointwell.DAO.DoctorDAO;
import com.appointwell.DAO.HospitalDAO;
import com.appointwell.POJO.Availability;
import com.appointwell.POJO.Doctor;
import com.appointwell.POJO.Hospital;
import com.appointwell.POJO.Patient;
import com.appointwell.Services.DoctorService;
import com.appointwell.Utils.AppointWellUtils;
import com.appointwell.Wrapper.DTOTransformer;
import com.appointwell.Wrapper.DoctorWrapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private DoctorDAO doctorDao;

    @Autowired
    private HospitalDAO hospitalDAO;

    @Autowired
    AvailabilityDAO availabilityDAO;

    @Override
    public ResponseEntity<String> signup(Map<String, String> requestMapping) {
        try {
            if (validRequestMap(requestMapping)) {
                Doctor doctor = doctorDao.findDoctorByEmail(requestMapping.get("email"));
                if (Objects.isNull(doctor)) {
                    doctorDao.save(getDoctorFromMap(requestMapping));
                    return AppointWellUtils.getResponseEntity("Doctor Signup Successful", HttpStatus.OK);
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
        // Implement login logic (similar to the UserServiceImpl)
        try {
            Doctor doctor = doctorDao.findDoctorByEmail(requestMapping.get("email"));
            if (doctor != null && doctor.getPassword().equals(requestMapping.get("password"))) {
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
    public ResponseEntity<List<DoctorWrapper>> getDoctorsBySpecialization(String requestMapping) {
        try {
            List<Doctor> doctors = doctorDao.findDoctorsBySpecialization(requestMapping);

            // Map each Doctor to a DoctorDTO
            List<DoctorWrapper> doctorDTOs = doctors.stream()
                    .map(DTOTransformer::toDoctorDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DoctorWrapper>> getAllDoctors() {
        try {
            List<Doctor> doctors = doctorDao.findAvailableDoctors();

            // Map each Doctor to a DoctorDTO
            List<DoctorWrapper> doctorDTOs = doctors.stream()
                    .map(DTOTransformer::toDoctorDTO)
                    .collect(Collectors.toList());

            return new ResponseEntity<>(doctorDTOs, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> update(Map<String, String> requestMapping) {
        try {
            Optional<Doctor> doctor = doctorDao.findById(Integer.parseInt(requestMapping.get("id")));
            if (doctor.isPresent()) {
//                doctorDao.updateDetails(requestMapping.get("details"), doctor.get().getId());
                return AppointWellUtils.getResponseEntity("Doctor Updated Successfully", HttpStatus.OK);
            } else {
                return AppointWellUtils.getResponseEntity("Doctor Not Found", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> changePassword(Map<String, String> requestMapping) {
        // Implement change password functionality (similar to the UserServiceImpl)
//        .info("Inside Patient Login Method :{}", requestMapping);
        try {
            Doctor doctor = doctorDao.findDoctorByEmail(requestMapping.get("email"));
            if (doctor != null && doctor.getPassword().equals(requestMapping.get("password"))) {
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
    public ResponseEntity<List<DoctorWrapper>> getAvailableDoctors(int doctorId) {
        try {
            List<DoctorWrapper> availableDoctors = doctorDao.findAvailableDoctorsByDoctorID(doctorId);
            return new ResponseEntity<>(availableDoctors, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<DoctorWrapper>> addAvailability(Availability availability) {
        try {
            //TODO
//            doctorDao.ad(availability);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    private boolean validRequestMap(Map<String, String> requestMapping) {
        return requestMapping.containsKey("name") && requestMapping.containsKey("email") && requestMapping.containsKey("contact_number");
    }

    private Doctor getDoctorFromMap(Map<String, String> requestMapping) {


        Doctor doctor = new Doctor();
        Hospital firstHospital = hospitalDAO.findAll(
                        PageRequest.of(0, 1, Sort.by("id").ascending())
                ).getContent().stream().findFirst()
                .orElseThrow(() -> new IllegalStateException("No hospitals found"));
        doctor.setName(requestMapping.get("name"));
        doctor.setEmail(requestMapping.get("email"));
        doctor.setContactNumber(requestMapping.get("contact_number"));
        doctor.setPassword(requestMapping.get("password"));
        doctor.setSpecialization(requestMapping.get("specialization"));
        doctor.setStatus(requestMapping.get("status"));
        doctor.setHospital(firstHospital);

        return doctor;
    }
}
