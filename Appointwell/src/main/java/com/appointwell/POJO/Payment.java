package com.appointwell.POJO;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDateTime;

@org.hibernate.annotations.NamedQuery(name="Payment.findByAppointmentId", query="SELECT p FROM Payment p WHERE p.appointmentId=:appointmentId")
@org.hibernate.annotations.NamedQuery(name="Payment.findAllPayments", query="SELECT p FROM Payment p")

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(name="payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "appointment_id")
    private int appointmentId;

    @Column(name = "amount")
    private double amount;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "status")
    private String status;  // e.g., "completed", "pending", "failed"

    @Column(name = "payment_method")
    private String paymentMethod;  // e.g., "Matera", "Credit Card"
}
