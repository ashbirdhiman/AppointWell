package com.appointwell.Services;

import com.appointwell.DAO.AdminDAO;
import com.appointwell.DAO.DoctorDAO;
import com.appointwell.DAO.HospitalDAO;
import com.appointwell.POJO.Admin;
import com.appointwell.POJO.Doctor;
import com.appointwell.POJO.Hospital;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AdminDAO adminDAO;

    @Autowired
    HospitalDAO hospitalDAO;

    @Autowired
    DoctorDAO doctorDAO;
    // Add a new hospital
    public String addHospital(Hospital hospital) {
        hospitalDAO.save(hospital);
        return "Hospital added successfully!";
    }

    // Admin login logic
    public String adminLogin(String email, String password) {
        Admin admin = adminDAO.findByEmail(email);

        if (admin == null) {
            return "Admin not found!";
        }

        if (!admin.getPassword().equals(password)) {
            return "Incorrect password!";
        }

        return "Login successful!";
    }

    // View all hospitals
    public List<Hospital> getHospitals() {
        return hospitalDAO.findAll();
    }

    // Assign doctor to hospital
    public String assignDoctorToHospital(int doctorId, int hospitalId) {
        Hospital hospital = hospitalDAO.findById(hospitalId).orElse(null);
        Doctor doctor = doctorDAO.findById(doctorId).orElse(null);

        if (hospital == null || doctor == null) {
            return "Hospital or Doctor not found!";
        }

        doctor.setHospital(hospital);
        doctorDAO.save(doctor);
        return "Doctor assigned to hospital successfully!";
    }
}
