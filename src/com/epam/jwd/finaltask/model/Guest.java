package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class Guest {
    private int guestId;
    private String name;
    private String mobile;
    private String email;
    private String address;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGuestId() {
        return guestId;
    }

    public void setGuestId(int guestId) {
        this.guestId = guestId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Guest{" +
                "Name= '" + name + '\'' +
                "Mobile= '" + mobile + '\'' +
                "Email= '" + email + '\'' +
                "Addess= '" + address + '\'' +
                "}";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Guest guest = (Guest) o;
        return guestId == guest.guestId &&
                userId == guest.userId &&
                Objects.equals(name, guest.name) &&
                Objects.equals(mobile, guest.mobile) &&
                Objects.equals(email, guest.email) &&
                Objects.equals(address, guest.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guestId, name, mobile, email, address, userId);
    }
}