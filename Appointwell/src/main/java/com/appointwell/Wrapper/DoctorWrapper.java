package com.appointwell.Wrapper;

public class DoctorWrapper {
    private int id;
    private String name;
    private String specialization;
    private String status;  // e.g., "available", "not available"

    public DoctorWrapper(int id, String name, String specialization, String status) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
