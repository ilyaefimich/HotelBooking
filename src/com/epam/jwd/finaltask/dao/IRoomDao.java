package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomDao {
    public List<Room> getAvailableRoomsByRoomTypeAndDates(int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate) throws DAOException;
    List<Room> getRooms() throws DAOException;
    List<RateType> getRateTypesByRoomType(int roomTypeId) throws DAOException;
    void delete(int roomid) throws DAOException;
    void create(String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress) throws DAOException;
    int update(int roomid, LocalDate checkInDate, LocalDate checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus) throws DAOException;
}

