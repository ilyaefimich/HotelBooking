package com.epam.jwd.finaltask.model;

public class Room {
    private int roomId;
    private String name;
    private RoomType roomType;

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name= '" + name + '\'' +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Room room = (Room) o;
        if (room != null ? !room.equals(room.name) : room.name != null) return false;
        return name != null ? name.equals(room.name) : room.name == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 32 * result + (name != null ? name.hashCode() : 0);
        return result;

    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;

    }


    public RoomType getRoomType() {
        return roomType;
    }



}