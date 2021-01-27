package com.epam.jwd.finaltask.model;

import java.time.LocalDate;

public class PreferedPaymentMethod {
    private int preferedPaymentMethodId;
    private String name;
    private String cardholderName;
    private LocalDate expirationDate;
    private int cardNumber;
    private int csvCode;

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public int getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(int cardNumber) {
        this.cardNumber = cardNumber;
    }

    public int getCsvCode() {
        return csvCode;
    }

    public void setCsvCode(int csvCode) {
        this.csvCode = csvCode;
    }

    public int getPreferedPaymentMethodId() {
        return preferedPaymentMethodId;
    }

    public void setPreferedPaymentMethodId(int preferedPaymentMethodId) {
        this.preferedPaymentMethodId = preferedPaymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PreferedPaymentMethod{" +
                "name= '" + name + '\'' +
                "cardHolderName'"+cardholderName+
                "expirationDate'"+expirationDate+
                "cardNumber'"+cardNumber+
                "csvCode" +csvCode+
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreferedPaymentMethod preferedPaymentMethod = (PreferedPaymentMethod) o;
        if (preferedPaymentMethod != null ? !preferedPaymentMethod.equals(preferedPaymentMethod.name) : preferedPaymentMethod.name != null) return false;
        return name != null ? name.equals(preferedPaymentMethod.name) : preferedPaymentMethod.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 32 * result + (name != null ? name.hashCode() : 0);
        return result;


    }
}