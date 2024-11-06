package com.appointwell.POJO;

import com.appointwell.POJO.Availability;
import com.appointwell.POJO.Hospital;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.io.Serializable;
import java.util.List;

//@org.hibernate.annotations.NamedQuery(name="Doctor.findBySpecialization", query="SELECT d FROM Doctor d WHERE d.specialization=:specialization")
//@org.hibernate.annotations.NamedQuery(name="Doctor.getAvailableDoctors", query="SELECT new com.appointwell.Wrapper.DoctorWrapper(d.id, d.name, d.specialization, d.status) FROM Doctor d WHERE d.status='available'")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Table(name="doctor")
public class Doctor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;


    @Column(name = "specialization")
    private String specialization;

    @Column(name = "contact_number")
    private String contactNumber;

    @Column(name = "status")
    private String status;

    // Many-to-One relationship with Hospital
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hospital_id", nullable = false)

    private Hospital hospital;

    // One-to-Many relationship with Availability
    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Availability> availabilities;
}
