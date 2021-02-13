package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class Room {
    private int roomId;
    private String name;
    private int price;
    private RoomType roomType;
    private RoomStatus roomStatus;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public RoomStatus getRoomStatus() {
        return roomStatus;
    }

    public void setRoomStatus(RoomStatus roomStatus) {
        this.roomStatus = roomStatus;
    }

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
        if (this == o){
            return true;
        }
        if (o == null || getClass() != o.getClass()){
            return false;
        }
        Room room = (Room) o;
        return roomId == room.roomId &&
                Objects.equals(name, room.name) &&
                Objects.equals(roomType, room.roomType) &&
                Objects.equals(roomStatus, room.roomStatus);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, name, roomType, roomStatus);
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;

    }


    public RoomType getRoomType() {
        return roomType;
    }
}