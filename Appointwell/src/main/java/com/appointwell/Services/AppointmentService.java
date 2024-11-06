package com.appointwell.Services;

import com.appointwell.POJO.Appointment;

import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface AppointmentService {

    ResponseEntity<String> bookAppointment(Map<String, String> requestMapping);

    ResponseEntity<List<Appointment>> getAppointmentsByPatient(int patientID);

    ResponseEntity<List<Appointment>> getAppointmentsByDoctor(int doctorID);

    ResponseEntity<List<Appointment>> getAllAppointments();

    ResponseEntity<String> cancelAppointment(Map<String, String> requestMapping);
}
