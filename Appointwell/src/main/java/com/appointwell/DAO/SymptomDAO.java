package com.appointwell.DAO;

import com.appointwell.POJO.Symptom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SymptomDAO extends JpaRepository<Symptom, Integer> {

    // Get all symptoms
    @Query("SELECT s FROM Symptom s")
    List<Symptom> getAllSymptoms();

    // Find symptom by name
    @Query("SELECT s FROM Symptom s WHERE s.name = :name")
    Symptom findBySymptomName(String name);

    @Query("SELECT s FROM Symptom s WHERE s.patientID = :id")
    Symptom findBySymptomByPatientID(int id);

}
