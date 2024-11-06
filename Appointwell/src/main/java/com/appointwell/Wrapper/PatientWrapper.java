package com.appointwell.Wrapper;

import com.appointwell.POJO.Appointment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class PatientWrapper {

    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private List<Appointment> appointmentList;

    // Update the constructor to match the fields selected in the query
    public PatientWrapper(int id, String name, String email, String contactNumber, List<Appointment> appointmentList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.contactNumber = contactNumber;
        this.appointmentList = appointmentList;
    }
}
