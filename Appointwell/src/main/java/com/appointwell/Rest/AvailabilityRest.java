package com.appointwell.Rest;

import com.appointwell.POJO.Availability;
import com.appointwell.Wrapper.AvailabilityWrapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/availability")
public interface AvailabilityRest {

    // Register a new availability
    @PostMapping(path = "/add")
    public ResponseEntity<String> addAvailability(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get availability by doctor
    @GetMapping(path = "/getByDoctor")
    public ResponseEntity<List<AvailabilityWrapper>> getAvailabilityByDoctor(@RequestBody(required = true) Map<String, String> requestMapping);

    // Get available slots for a doctor
    @GetMapping(path = "/getAvailableSlots")
    public ResponseEntity<List<AvailabilityWrapper>> getAvailableSlots(@RequestBody(required = true) Map<String, String> requestMapping);
}
