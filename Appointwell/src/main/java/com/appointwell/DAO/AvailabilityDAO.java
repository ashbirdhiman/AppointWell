package com.appointwell.DAO;

import com.appointwell.POJO.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AvailabilityDAO extends JpaRepository<Availability, Integer> {

    // Find all availabilities for a doctor
    @Query("SELECT a FROM Availability a WHERE a.doctor.id = :doctorId")
    List<Availability> findAvailabilitiesByDoctorId(int doctorId);

    // Find available timeslots for a doctor
    @Query("SELECT a FROM Availability a WHERE  a.doctor.id = :doctorId")
    List<Availability> findAvailableTimeslotsByDoctorId(int doctorId);
}
