package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.RoomStatus;
import com.epam.jwd.finaltask.model.RoomType;

import java.util.List;
/**
 * Provides methods for application to manage rooms.
 */
public interface IRoomService {
    List<Room> getReadyToUseRooms();

    List<Room> getAllRooms();

    List<Room> getAvailableRoomsByRoomTypeAndDates(int guestTypeId, int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate);
    List<RateType> getRateTypesByRoomType(int roomTypeId);
    List<RoomType> getRoomTypes();
    List<RoomStatus> getRoomStatuses();

    Room getRoomById(int roomId);
    void delete(int roomid);
    void update(int roomId, String roomName, int roomStatus, int roomType);
    void create(String roomName, int roomStatus, int roomType);

}
