package com.epam.jwd.finaltask.service.impl;

import com.epam.jwd.finaltask.dao.IRoomDao;
import com.epam.jwd.finaltask.dao.impl.RoomDaoImpl;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.RoomStatus;
import com.epam.jwd.finaltask.model.RoomType;
import com.epam.jwd.finaltask.service.IRoomService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

public class RoomServiceImpl implements IRoomService {
    static final Logger LOGGER = LoggerFactory.getLogger(RoomServiceImpl.class);
    private IRoomDao roomDao = new RoomDaoImpl();

    @Override
    public List<Room> getReadyToUseRooms() {
        try {
            List<Room> rooms = roomDao.getRooms();
            rooms = rooms
                    .stream()
                    .filter(Room -> Room.getRoomStatus().getRoomStatusId() == 1)
                    .collect(Collectors.toList());
            return rooms;
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getReadyToUseRooms(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Room> getAllRooms() {
        try {
            return roomDao.getRooms();
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getAllRooms(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Room> getAvailableRoomsByRoomTypeAndDates(int guestTypeId, int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate) {
        try {
            return roomDao.getAvailableRoomsByRoomTypeAndDates(guestTypeId, roomTypeId, rateTypeId, checkinDate, checkoutDate);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getAvailableRoomsByRoomTypeAndDates(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RateType> getRateTypesByRoomType(int roomTypeId) {
        try {
            return roomDao.getRateTypesByRoomType(roomTypeId);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getRateTypesByRoomType(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RoomType> getRoomTypes() {
        try {
            return roomDao.getRoomTypes();
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getRoomTypes(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<RoomStatus> getRoomStatuses() {
        try {
            return roomDao.getRoomStatuses();
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getRoomStatuses: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Room getRoomById(int roomId) {
        try {
            List<Room> rooms = roomDao.getRooms();
            for (Room room : rooms) {
                if (room.getRoomId() == roomId) {
                    return room;
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getRoomById(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public void update(int roomId, String roomName, int roomStatus, int roomType) {
        try {
            roomDao.update(roomId, roomName, roomStatus, roomType);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method updateRoom(): " + e.getMessage());
        }

    }

    @Override
    public void create(String roomName, int roomStatus, int roomType) {
        try {
            roomDao.create(roomName, roomType, roomStatus);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method createRoom(): " + e.getMessage());
        }

    }

    @Override
    public void delete(int roomid) {
        try {
            roomDao.delete(roomid);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method delete(): " + e.getMessage());
        }
    }
}
