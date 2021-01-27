package com.epam.jwd.finaltask.model;

public class RoomType {
    private int roomTypeId;
    private String name;

    public int getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(int roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "RoomType{" +
                "type= '" + name + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoomType roomType = (RoomType) o;
        if (roomType != null ? !roomType.equals(roomType.name) : roomType.name != null) return false;
        return name != null ? name.equals(roomType.name) : roomType.name == null;


    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 32 * result + (name != null ? name.hashCode() : 0);
        return result;


    }

}