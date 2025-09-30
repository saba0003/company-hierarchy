package com.solvd.companyhierarchy.exception;

public class DepartmentFullException extends RuntimeException {

    public DepartmentFullException(String message) {
        super(message);
    }
}
