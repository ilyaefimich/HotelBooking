package com.epam.jwd.finaltask.dao.impl;

import com.epam.jwd.finaltask.dao.IRoomDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.RoomStatus;
import com.epam.jwd.finaltask.model.RoomType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class RoomDaoImpl implements IRoomDao {

    static final Logger LOGGER = LoggerFactory.getLogger(RoomDaoImpl.class);

    private static final String DELETE_ROOM = "DELETE FROM `web`.`room`\n" +
            "    WHERE roomId = ?;";

    private static final String UPDATE_ROOM = "   UPDATE `web`.`room`\n" +
            "   SET\n" +
            "                     name = ?,\n" +
            "                        roomTypeId = ?,\n" +
            "                        roomStatusId =? \n" +
            "                WHERE roomId = ?";

    private static final String CREATE_ROOM = "CREATE `web`.`room`\n" +
            "    SET\n" +
            "            name = ?,\n" +
            "            roomType = ?,\n" +
            "            roomStatus = ?,\n" +
            "    WHERE roomId = ?";


    private static final String INSERT_ROOM = "INSERT INTO `web`.`room` ( `name`, `roomTypeId`,`roomStatusId` ) " +
            "VALUES (?, ?, ?)";


    private static final String SELECT_ROOMS =
            "SELECT rt.name roomTypeName, r.roomId, r.name roomName, st.roomStatusId, st.name, rt.roomTypeId \n" +
                    "FROM room b  \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId  \n" +
                    "LEFT OUTER JOIN roomStatus st ON st.roomStatusId = b.roomStatusId  \n" +
                    "LEFT OUTER JOIN room r ON r.roomId = b.roomId \n";

    private static final String SELECT_AVAILABLE_ROOMS =
            "SELECT DISTINCT r.roomId, concat(r.name,  \" (\",  rp.priority ,  \")\") room, rat.price, rt.name, rat" +
                    ".ratetypeId " +
                    "FROM web.room r\n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = r.roomTypeId\n" +
                    "LEFT OUTER JOIN roomavailability ra ON ra.roomId = r.roomId\n" +
                    "LEFT OUTER JOIN rate rat ON rat.roomTypeId = r.roomTypeId\n" +
                    "LEFT OUTER JOIN roompriority rp ON rp.roomId = r.roomId\n" +
                    "WHERE r.roomStatusId = 1 AND rt.roomTypeId = ? AND rat.ratetypeId = ? \n" +
                    "AND (ra.date NOT BETWEEN ? AND ? OR ra.date IS NULL) \n" +
                    "AND rp.guesttype = ? \n" +
                    "order by rp.priority desc";

    private static final String SELECT_RATE_TYPES =
            "SELECT rt.ratetypeId, rt.name\n" +
                    "FROM web.rate r\n" +
                    "LEFT OUTER JOIN web.ratetype rt ON rt.ratetypeId = r.ratetypeId\n" +
                    "WHERE r.roomTypeId = ?";

    private static final String SELECT_ROOM_TYPES =
            "SELECT roomTypeId, name\n" +
                    " FROM web.roomtype";

    private static final String SELECT_ROOM_STATUSES =
            "SELECT roomStatusId, name\n" +
                    " FROM web.roomstatus";


    @Override
    public List<RoomType> getRoomTypes() throws DAOException {
        List<RoomType> roomTypes = null;
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_ROOM_TYPES)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        roomTypes = new ArrayList<RoomType>();
                        RoomType roomType;
                        while (rs.next()) {
                            roomType = new RoomType();
                            roomType.setRoomTypeId(rs.getInt(1));
                            roomType.setName(rs.getString(2));
                            roomTypes.add(roomType);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error. Impossible to load Room Types: " + e);
        }
        return roomTypes;
    }

    @Override
    public List<RoomStatus> getRoomStatuses() throws DAOException {
        List<RoomStatus> roomStatuses = null;
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_ROOM_STATUSES)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        roomStatuses = new ArrayList<RoomStatus>();
                        RoomStatus roomStatus;
                        while (rs.next()) {
                            roomStatus = new RoomStatus();
                            roomStatus.setRoomStatusId(rs.getInt(1));
                            roomStatus.setName(rs.getString(2));
                            roomStatuses.add(roomStatus);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error. Impossible to load Room Statuses: " + e);
        }
        return roomStatuses;
    }

    @Override
    public List<RateType> getRateTypesByRoomType(int roomTypeId) throws DAOException {
        List<RateType> rateTypes = null;
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_RATE_TYPES)) {
                    statement.setInt(1, roomTypeId);
                    try (ResultSet rs = statement.executeQuery()) {
                        rateTypes = new ArrayList<RateType>();
                        RateType rateType;
                        while (rs.next()) {
                            rateType = new RateType();
                            rateType.setRateTypeId(rs.getInt(1));
                            rateType.setRateName(rs.getString(2));

                            rateTypes.add(rateType);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error. Impossible to load Rate Types: " + e);
        }
        return rateTypes;
    }

    @Override
    public List<Room> getAvailableRoomsByRoomTypeAndDates(int guestTypeId, int roomTypeId, int rateTypeId, String checkinDate,
                                                          String checkoutDate) throws DAOException {
        List<Room> rooms = null;
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_ROOMS)) {
                    statement.setInt(1, roomTypeId);
                    statement.setInt(2, rateTypeId);
                    statement.setDate(3, Date.valueOf(checkinDate));
                    statement.setDate(4, Date.valueOf(checkoutDate));
                    statement.setInt(5, guestTypeId);
                    try (ResultSet rs = statement.executeQuery()) {
                        rooms = new ArrayList<Room>();
                        Room room;
                        while (rs.next()) {
                            room = new Room();
                            room.setRoomId(rs.getInt(1));
                            room.setName(rs.getString(2));
                            room.setPrice(rs.getInt(3));

                            rooms.add(room);
                        }
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when loading rooms: " + e);
        }
        return rooms;
    }


    @Override
    public List<Room> getRooms() throws DAOException {
        List<Room> rooms = null;
        try {
            try (Connection connection = ConnectionPool.getInstance().getConnection()) {
                try (PreparedStatement statement = connection.prepareStatement(SELECT_ROOMS)) {
                    try (ResultSet rs = statement.executeQuery()) {
                        rooms = new ArrayList<Room>();
                        Room room;
                        while (rs.next()) {
                            room = new Room();
                            room.setName(rs.getString(3));
                            room.setRoomId(rs.getInt(2));
                            RoomType roomType = new RoomType();
                            roomType.setName(rs.getString(1));
                            roomType.setRoomTypeId(rs.getInt(6));
                            room.setRoomType(roomType);
                            RoomStatus roomStatus = new RoomStatus();
                            roomStatus.setRoomStatusId(rs.getInt(4));
                            roomStatus.setName(rs.getString(5));
                            room.setRoomStatus(roomStatus);
                            rooms.add(room);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error. Impossible to load rooms: " + e);
        }
        return rooms;
    }

    @Override
    public void delete(int roomid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_ROOM)) {
                statement.setInt(1, roomid);
                statement.executeUpdate();
                statement.closeOnCompletion();
            }

        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when deleting a room: " + e);
        }
    }

    @Override
    public void create(String roomName, int roomType, int roomStatus) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(INSERT_ROOM)) {
                statement.setString(1, roomName);
                statement.setInt(2, roomType);
                statement.setInt(3, roomStatus);
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException("Error when creating a room: " + e);
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException("Error when deleting a room: " + e);
        }
    }

    @Override
    public void update(int roomId, String roomName, int roomStatus, int roomType) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            try (PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM)) {

                statement.setString(1, roomName);
                statement.setInt(2, roomType);
                statement.setInt(3, roomStatus);
                statement.setInt(4, roomId);
                statement.executeUpdate();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage());
                throw new DAOException("Error when updating a room: " + e);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }


    }


}
