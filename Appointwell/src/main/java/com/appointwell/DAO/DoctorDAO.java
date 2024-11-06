package com.appointwell.DAO;

import com.appointwell.POJO.Doctor;
import com.appointwell.POJO.Patient;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DoctorDAO extends JpaRepository<Doctor, Integer> {


    @Query("SELECT d FROM Doctor d WHERE d.email = :email")
    Doctor findDoctorByEmail(String email);

    // Find doctors by specialization
    @Query("SELECT d FROM Doctor d WHERE d.specialization = :specialization")
    List<Doctor> findDoctorsBySpecialization(String specialization);

    // Find available doctors
    @Query("SELECT d FROM Doctor d WHERE d.status = 'available'")
    List<Doctor> findAvailableDoctors();

    @Query("SELECT d FROM Doctor d WHERE d.status = 'available' and d.id =:doctorId")
    List<DoctorWrapper> findAvailableDoctorsByDoctorID(int doctorId);
}
