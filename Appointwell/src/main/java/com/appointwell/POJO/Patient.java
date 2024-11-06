package com.appointwell.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.io.Serializable;
import java.util.List;


//@NamedQuery(name="Patient.findByEmail", query="SELECT p FROM Patient p WHERE p.email=:email")
//@NamedQuery(name="Patient.getAllPatients", query="SELECT new com.appointwell.Wrapper.PatientWrapper(p.id, p.name, p.email, p.contactNumber,p.appointments) FROM Patient p")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="patient")
public class Patient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Appointment> appointments;

}
