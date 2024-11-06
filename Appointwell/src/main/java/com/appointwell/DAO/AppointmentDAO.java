package com.appointwell.DAO;

import com.appointwell.POJO.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDAO extends JpaRepository<Appointment, Integer> {

    // Get all appointments for a specific patient
    @Query("SELECT a FROM Appointment a WHERE a.patientId = :patientId")
    List<Appointment> getAppointmentsByPatientId(int patientId);

    // Get all appointments for a specific doctor
    @Query("SELECT a FROM Appointment a WHERE a.doctorId = :doctorId")
    List<Appointment> getAppointmentsByDoctorId(int doctorId);

    // Get appointment by patient and doctor
    @Query("SELECT a FROM Appointment a WHERE a.patient.id = :patientId AND a.doctorId = :doctorId")
    Appointment getAppointmentByPatientAndDoctor(int patientId, int doctorId);
}
