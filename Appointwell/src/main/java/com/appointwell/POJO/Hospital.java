package com.appointwell.POJO;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;


import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

//@org.hibernate.annotations.NamedQuery(name="Hospital.findNearbyHospitals", query="SELECT h FROM Hospital h WHERE h.distance <= :maxDistance")
//@org.hibernate.annotations.NamedQuery(name="Hospital.getHospitalsWithWaitTimes", query="SELECT new com.appointwell.POJO.Hospital(h.id, h.name, h.location, h.waitTime) FROM Hospital h")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="hospital")
//@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Hospital implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    // One-to-Many relationship with Doctor
//    @JsonIgnore
    @OneToMany(mappedBy = "hospital", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Doctor> doctors;

    @Column(name = "address")
    private String address;

    @Column(name = "wait_time")
    private int waitTime;  // in minutes

}
