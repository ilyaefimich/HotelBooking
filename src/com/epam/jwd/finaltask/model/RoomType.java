package com.epam.jwd.finaltask.model;

import java.util.Objects;

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
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        RoomType roomType = (RoomType) o;
        return roomTypeId == roomType.roomTypeId &&
                Objects.equals(name, roomType.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomTypeId, name);
    }
}