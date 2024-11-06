package com.appointwell.DAO;

import com.appointwell.POJO.Doctor;
import com.appointwell.POJO.Hospital;
import com.appointwell.Wrapper.DoctorWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HospitalDAO extends JpaRepository<Hospital, Integer> {

    // Find hospital by name
    @Query("SELECT h FROM Hospital h WHERE h.name = :name")
    Hospital findByHospitalName(String name);

    // Get all hospitals in a specific location
    @Query("SELECT h FROM Hospital h WHERE h.latitude = :latitude and h.longitude= :longitude")

    List<Hospital> findHospitalByLongitudeandLatitude(String longitude,String latitude);

    @Query("SELECT d FROM Doctor d WHERE d.hospital.id = :hospitalId")
    List<Doctor> findDoctorsByHospitalId(int hospitalId);
}
