package com.appointwell.Wrapper;

import java.time.LocalDateTime;

public class AvailabilityWrapper {
    private LocalDateTime dateTime;
    private boolean isAvailable;  // Indicating if the doctor is available at this time

    // Constructor, getters and setters


    public AvailabilityWrapper(LocalDateTime dateTime, boolean isAvailable) {
        this.dateTime = dateTime;
        this.isAvailable = isAvailable;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}
