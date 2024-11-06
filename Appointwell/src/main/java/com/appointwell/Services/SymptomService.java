package com.appointwell.Services;

import com.appointwell.POJO.Symptom;
import com.appointwell.Wrapper.SymptomWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface SymptomService {

    /**
     * Adds a new symptom to the system.
     *
     * @param requestMapping A map containing the details of the symptom.
     * @return A string message indicating success or failure.
     */
    ResponseEntity<String> addSymptom(Map<String, String> requestMapping);

    /**
     * Retrieves all symptoms from the system.
     *
     * @return A list of SymptomWrapper objects representing all symptoms.
     */
    ResponseEntity<List<Symptom>> getAllSymptoms();

    /**
     * Retrieves a specific symptom by its name.
     *
     * @param requestMapping A map containing the name of the symptom.
     * @return A SymptomWrapper object representing the symptom.
     */
    ResponseEntity<Symptom> getSymptomByName(Map<String, String> requestMapping);

    ResponseEntity<Symptom> findBySymptomByPatientID(int id);
}
