package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class GuestType {
    int guestTypeId;
    String name;

    public int getGuestTypeId() {
        return guestTypeId;
    }

    public void setGuestTypeId(int guestTypeId) {
        this.guestTypeId = guestTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GuestType{" +
                "guestTypeId=" + guestTypeId +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestType guestType = (GuestType) o;
        return guestTypeId == guestType.guestTypeId &&
                Objects.equals(name, guestType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestTypeId, name);
    }
}
