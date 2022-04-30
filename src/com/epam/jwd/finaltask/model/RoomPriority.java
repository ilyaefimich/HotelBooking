package com.epam.jwd.finaltask.model;

import java.util.Objects;

public class RoomPriority {
    private int roompriorityId;
    private int guesttypeId;
    private int expertId;
    private int roomId;
    private double priority;

    public int getExpertId() {
        return expertId;
    }

    public void setExpertId(int expertId) {
        this.expertId = expertId;
    }

    public RoomPriority() {
    }

    public RoomPriority(int guesttypeId, int expertId, int roomId, double priority) {
        this.guesttypeId = guesttypeId;
        this.expertId = expertId;
        this.roomId = roomId;
        this.priority = priority;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoompriorityId() {
        return roompriorityId;
    }

    public void setRoompriorityId(int roompriorityId) {
        this.roompriorityId = roompriorityId;
    }

    public int getGuesttypeId() {
        return guesttypeId;
    }

    public void setGuesttypeId(int guesttypeId) {
        this.guesttypeId = guesttypeId;
    }

    public double getPriority() {
        return priority;
    }

    public void setPriority(double priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "RoomPriority{" +
                "roompriorityId=" + roompriorityId +
                ", guesttype=" + guesttypeId +
                ", priority=" + priority +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoomPriority that = (RoomPriority) o;
        return roompriorityId == that.roompriorityId &&
                guesttypeId == that.guesttypeId &&
                Double.compare(that.priority, priority) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roompriorityId, guesttypeId, priority);
    }
}
