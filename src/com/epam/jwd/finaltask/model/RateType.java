package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class RateType {
    private int rateTypeId;
    private String rateName;

    public int getRateTypeId() {
        return rateTypeId;
    }

    public void setRateTypeId(int rateTypeId) {
        this.rateTypeId = rateTypeId;
    }

    public String getRateName() {
        return rateName;
    }

    public void setRateName(String rateName) {
        this.rateName = rateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RateType rateType = (RateType) o;
        return rateTypeId == rateType.rateTypeId &&
                Objects.equals(rateName, rateType.rateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rateTypeId, rateName);
    }
}
