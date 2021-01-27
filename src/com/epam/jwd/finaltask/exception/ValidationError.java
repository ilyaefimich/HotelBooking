package com.epam.jwd.finaltask.exception;

public class ValidationError extends java.lang.Error {
    private com.epam.jwd.finaltask.model.ValidationError validationError;
    public final static int CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE = 123;
    private int errorCode;

    public int getErrorCode() {
        return errorCode;
    }

    public ValidationError(int errorCode) {
        this.errorCode = errorCode;
    }

}
