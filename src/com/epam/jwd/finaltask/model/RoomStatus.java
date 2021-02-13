package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class RoomStatus {
    private int roomStatusId;
    private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        RoomStatus that = (RoomStatus) o;
        return roomStatusId == that.roomStatusId &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomStatusId, name);
    }

    public int getRoomStatusId() {
        return roomStatusId;
    }

    public void setRoomStatusId(int roomStatusId) {
        this.roomStatusId = roomStatusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
