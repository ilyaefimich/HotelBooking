package com.epam.jwd.finaltask.service.impl;

import com.epam.jwd.finaltask.dao.IRoomDao;
import com.epam.jwd.finaltask.dao.impl.RoomDaoImpl;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.*;
import com.epam.jwd.finaltask.service.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class RoomServiceImpl implements IRoomService {
    static final Logger logger = LoggerFactory.getLogger(RoomServiceImpl.class);
    private IRoomDao roomDao = new RoomDaoImpl();

    @Override
    public List<Room> getRooms() {
        try {
            return roomDao.getRooms();
        } catch (DAOException e) {
            logger.error("Error when calling the method getRooms(): " +e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Room> getAvailableRoomsByRoomTypeAndDates(int roomTypeId, String checkinDate, String checkoutDate) {
        try {
            return roomDao.getAvailableRoomsByRoomTypeAndDates(roomTypeId, checkinDate, checkoutDate);
        } catch (DAOException e) {
            logger.error("Error when calling the method getAvailableRoomsByRoomTypeAndDates(): " +e.getMessage());
            e.printStackTrace();
        }
        return null;
    }
    @Override
    public int update(int roomid, LocalDate checkInDate, LocalDate checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus) {
        try {
            return roomDao.update(roomid,
                    checkInDate,
                    checkOutDate,
                    adultsCount,
                    guestName,
                    guestEmail,
                    guestMobile,
                    guestAddress,
                    newStatus);
        } catch (DAOException e) {
            logger.error("Error when calling the method updateUsers(): " + e.getMessage());
            e.printStackTrace();
        }
        return -1;
    }

    public static void main(String[] args) {
        IRoomService roomService = new RoomServiceImpl();
        List<Room> rooms = roomService.getRooms();

        for (Room room : rooms) {
            logger.debug(room.toString());
            //System.out.println(booking);
        }

    }
}
