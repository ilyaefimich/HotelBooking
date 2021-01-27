package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class ValidationError {
    String errorName;
    final static int CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE = 1;

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValidationError error = (ValidationError) o;
        return Objects.equals(errorName, error.errorName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(errorName);
    }
}
