package com.epam.jwd.finaltask.exception;

/**
 * ValidationError is used to throw application validation errors.
 */
public class ValidationError extends java.lang.Error {
    public static final String CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE = "Checkin date has to be before checkout date";
    private int errorCode;
    private String errorDescription;


    public int getErrorCode() {
        return errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public ValidationError(int errorCode) {
        this.errorCode = errorCode;
    }

    public ValidationError(String errorDescription) {
        this.errorDescription = errorDescription;
    }
}
