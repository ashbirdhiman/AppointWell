package com.appointwell.DAO;

import com.appointwell.POJO.Patient;
import com.appointwell.Wrapper.PatientWrapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Integer> {

    // Find patient by name
    @Query("SELECT p FROM Patient p WHERE p.name = :name")
    Patient findPatientByName(String name);

    @Query("SELECT p FROM Patient p WHERE p.email = :email")
    Patient findPatientByEmail(String email);

    // Get all patients
    @Query("SELECT p FROM Patient p")
    List<PatientWrapper> getAllPatients();



}
