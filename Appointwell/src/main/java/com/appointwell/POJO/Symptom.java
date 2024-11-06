package com.appointwell.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.io.Serializable;
import java.time.LocalDateTime;

@org.hibernate.annotations.NamedQuery(name="Symptom.getAllSymptoms", query="SELECT s FROM Symptom s")
@org.hibernate.annotations.NamedQuery(name="Symptom.findBySymptomName", query="SELECT s FROM Symptom s WHERE s.name=:name")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="symptom")
public class Symptom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "severity")
    private String severity;  // e.g., "mild", "moderate", "severe"

    @Column(name = "patient_id")
    private int patientID;  // e.g., "mild", "moderate", "severe"

}
