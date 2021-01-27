package com.epam.jwd.finaltask.model;

public class Guest {
    private String name;
    private String mobile;
    private String email;
    private String address;


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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Guest guest = (Guest) o;
        if (guest != null ? !guest.equals(guest.name) : guest.name != null) return false;
        return name != null ? name.equals(guest.name) : guest.name == null;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 32 * result + (name != null ? name.hashCode() : 0);
        return result;

    }


}