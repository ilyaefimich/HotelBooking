package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class PreferedPaymentMethod {
    private int preferedPaymentMethodId;
    private String name;
    private String cardholderName;
    private String expirationDate;
    private int cardNumber;
    private int csvCode;
    private int PaymentMethodId;

    public int getPaymentMethodId() {
        return PaymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        PaymentMethodId = paymentMethodId;
    }

    public String getCardholderName() {
        return cardholderName;
    }

    public void setCardholderName(String cardholderName) {
        this.cardholderName = cardholderName;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
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
                "cardHolderName'" + cardholderName +
                "expirationDate'" + expirationDate +
                "cardNumber'" + cardNumber +
                "csvCode" + csvCode +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PreferedPaymentMethod that = (PreferedPaymentMethod) o;
        return preferedPaymentMethodId == that.preferedPaymentMethodId &&
                cardNumber == that.cardNumber &&
                csvCode == that.csvCode &&
                PaymentMethodId == that.PaymentMethodId &&
                Objects.equals(name, that.name) &&
                Objects.equals(cardholderName, that.cardholderName) &&
                Objects.equals(expirationDate, that.expirationDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(preferedPaymentMethodId, name, cardholderName, expirationDate, cardNumber, csvCode, PaymentMethodId);
    }
}
