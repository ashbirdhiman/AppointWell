package com.appointwell.Wrapper;

public class SymptomWrapper {
    private int id;
    private String name;
    private String severity;  // e.g., "mild", "moderate", "severe"

    public SymptomWrapper(int id, String name, String severity) {
        this.id = id;
        this.name = name;
        this.severity = severity;
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

    public String getSeverity() {
        return severity;
    }

    public void setSeverity(String severity) {
        this.severity = severity;
    }
}
