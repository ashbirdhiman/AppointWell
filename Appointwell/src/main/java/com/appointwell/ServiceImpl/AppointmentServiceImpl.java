package com.appointwell.ServiceImpl;

import com.appointwell.DAO.AppointmentDAO;
import com.appointwell.DAO.SymptomDAO;
import com.appointwell.POJO.Appointment;
import com.appointwell.POJO.Symptom;
import com.appointwell.Services.AppointmentService;
import com.appointwell.Utils.AppointWellUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    @Autowired
    private AppointmentDAO appointmentDAO;

    @Autowired
    private SymptomDAO symptomDAO;

    @Override
    public ResponseEntity<String> bookAppointment(Map<String, String> requestMapping) {
        try {
            // Extract relevant information from the request
            String patient_Id = requestMapping.get("patientID");
            String doctor_Id = requestMapping.get("doctorID");  // Assuming doctorEmail is passed as doctor_Id
            String dateTime = requestMapping.get("dateTime");
            String hospitalID = requestMapping.get("hospitalID");

            // Create a Symptom object based on extracted data
            Symptom symptomFromPatient = symptomDAO.findBySymptomByPatientID(1);

//            Symptom symptom=new Symptom();
//            symptom.setName(symptomFromPatient.getName());
//            symptom.setSeverity(symptomFromPatient.getSeverity());
//            symptom.setPatientID(symptomFromPatient.getPatientID());  // Link symptom to the patient

            // Create a new Appointment object
            Appointment appointment = new Appointment();
            appointment.setPatientId(Integer.parseInt(patient_Id));
            appointment.setDoctorId(Integer.parseInt(doctor_Id));
            appointment.setDate(LocalDateTime.parse(dateTime));
            appointment.setHospitalName(hospitalID);

            // Create an ArrayList for symptoms and add the created symptom
            ArrayList<Symptom> symptoms = new ArrayList<>();
            symptoms.add(symptomFromPatient);
            appointment.setSymptoms(symptoms);

            // Save the appointment in the database
            appointmentDAO.save(appointment);
            return AppointWellUtils.getResponseEntity("Appointment booked successfully", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Failed to book the appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<Appointment>> getAppointmentsByPatient(int patientID) {
        try {
            List<Appointment> appointments = appointmentDAO.getAppointmentsByPatientId(patientID);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Appointment>> getAppointmentsByDoctor(int doctor_Id) {
        try {

            List<Appointment> appointments = appointmentDAO.getAppointmentsByDoctorId(doctor_Id);
//            List<AppointmentWrapper> appointmentWrappers = AppointmentWrapper.convertToWrapperList(appointments);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Appointment>> getAllAppointments() {
        try {
            List<Appointment> appointments = appointmentDAO.findAll();
//            List<AppointmentWrapper> appointmentWrappers = AppointmentWrapper.convertToWrapperList(appointments);
            return new ResponseEntity<>(appointments, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> cancelAppointment(Map<String, String> requestMapping) {
        try {
            String appointmentId = requestMapping.get("appointmentId");

            Optional<Appointment> appointmentOptional = appointmentDAO.findById(Integer.valueOf(appointmentId));
            if (appointmentOptional.isPresent()) {
                Appointment appointment = appointmentOptional.get();
                appointmentDAO.delete(appointment);
                return AppointWellUtils.getResponseEntity("Appointment canceled successfully", HttpStatus.OK);
            } else {
                return AppointWellUtils.getResponseEntity("Appointment not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AppointWellUtils.getResponseEntity("Failed to cancel the appointment", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
