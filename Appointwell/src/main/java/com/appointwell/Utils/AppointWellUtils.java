package com.appointwell.Utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class AppointWellUtils {

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus status) {
        return new ResponseEntity<>(message, status);
    }

    public static ResponseEntity<String> getSuccessResponseEntity(String message) {
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public static ResponseEntity<String> getErrorResponseEntity(String message) {
        return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
