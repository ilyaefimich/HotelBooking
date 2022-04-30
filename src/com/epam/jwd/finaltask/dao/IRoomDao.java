package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.RoomStatus;
import com.epam.jwd.finaltask.model.RoomType;

import java.util.List;

public interface IRoomDao {
    List<Room> getAvailableRoomsByRoomTypeAndDates(int guestTypeId, int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate) throws DAOException;
    List<RoomType> getRoomTypes() throws DAOException;
    List<RoomStatus> getRoomStatuses() throws DAOException;
    List<RateType> getRateTypesByRoomType(int roomTypeId) throws DAOException;
    List<Room> getRooms() throws DAOException;

    void delete(int roomid) throws DAOException;
    void create(String roomName, int roomType, int roomStatus) throws DAOException;
    void update(int roomId, String roomName, int roomStatus, int roomType) throws DAOException;


}

