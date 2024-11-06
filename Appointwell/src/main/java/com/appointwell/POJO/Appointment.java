package com.appointwell.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@org.hibernate.annotations.NamedQuery(name="Appointment.findByPatientId", query="SELECT a FROM Appointment a WHERE a.patientId=:patientId")
@org.hibernate.annotations.NamedQuery(name="Appointment.findByDoctorIdAndDate", query="SELECT a FROM Appointment a WHERE a.doctorId=:doctorId AND a.date=:date")
@org.hibernate.annotations.NamedQuery(name="Appointment.updateStatus", query="UPDATE Appointment a SET a.status=:status WHERE a.id=:id")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="appointment")
public class Appointment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "patient_id")
    private int patientId;

    @Column(name = "doctor_id")
    private int doctorId;

    @Column(name = "hospital_id")
    private String hospitalName;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "status")
    private String status; // e.g

    @Column(name = "payment_status")
    private String paymentStatus;

    @Column(name = "appointment_type")
    private String appointmentType;  // e.g., "online" or "offline"

    @ManyToMany
    @JoinTable(
            name = "appointment_symptom",
            joinColumns = @JoinColumn(name = "appointment_id"),
            inverseJoinColumns = @JoinColumn(name = "symptom_id")
    )
    private List<Symptom> symptoms;

    @ManyToOne(fetch = FetchType.LAZY)  // Many appointments can be associated with one patient
    @JoinColumn(name = "Patient_appointment_id", referencedColumnName = "id")  // Foreign key to Patient table
    private Patient patient;  // The reference to the Patient object


}
