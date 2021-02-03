package com.epam.jwd.finaltask.dao.impl;

import com.epam.jwd.finaltask.dao.IRoomDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class RoomDaoImpl implements IRoomDao {

    static final Logger logger = LoggerFactory.getLogger(RoomDaoImpl.class);

    private final static String DELETE_ROOM = "DELETE FROM `web`.`room`\n" +
            "    WHERE roomId = ?;";

    private final static String UPDATE_ROOM = "UPDATE `web`.`room`\n" +
            "    SET\n" +
            "            checkInDate = ?,\n" +
            "            checkOutDate = ?,\n" +
            "            adultsCount = ?,\n" +
            "            childrenCount = ?,\n" +
            "            comment = ?,\n" +
            "            roomStatusId = ?,\n" +
            "            roomTypeId = ?,\n" +
            "            GuestId = ?\n" +
            "    WHERE roomId = ?;";

    private final static String INSERT_ROOM = "INSERT INTO `web`.`room` (`checkInDate`, `checkOutDate`, `adultsCount`, `childrenCount`, `comment`, `roomStatusId`, `roomTypeId`, `GuestId`) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    private final static String SELECT_ROOMS =
            "SELECT  rt.name roomTypeName,  \n" +
                    "r.roomId, r.name roomName, \n" +
                    "FROM room b \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId \n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = b.roomTypeId \n" +
                    "LEFT OUTER JOIN room r ON r.roomId = b.roomId \n";

    private final static String SELECT_AVAILABLE_ROOMS =
            "SELECT DISTINCT r.roomId, r.name, rat.price\n" +
                    "FROM web.room r\n" +
                    "LEFT OUTER JOIN roomType rt ON rt.roomTypeId = r.roomTypeId\n" +
                    "LEFT OUTER JOIN roomavailability ra ON ra.roomId = r.roomId\n" +
                    "LEFT OUTER JOIN rate rat ON rat.roomTypeId = r.roomTypeId\n" +
                    "WHERE r.roomStatusId = 1 AND rt.roomTypeId = ? AND rat.ratetypeId = ? AND (ra.date NOT BETWEEN ? AND ? OR ra.date IS NULL)";

    private final static String SELECT_RATE_TYPES =
            "SELECT rt.ratetypeId, rt.name\n" +
                    "FROM web.rate r\n" +
                    "LEFT OUTER JOIN web.ratetype rt ON rt.ratetypeId = r.ratetypeId\n" +
                    "WHERE r.roomTypeId = ?;";

    @Override
    public List<RateType> getRateTypesByRoomType(int roomTypeId) throws DAOException {
        List<RateType> rateTypes = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_RATE_TYPES);
            statement.setInt(1, roomTypeId);
            ResultSet rs = statement.executeQuery();

            rateTypes = new ArrayList<RateType>();
            while (rs.next()) {
                RateType rateType = new RateType();
                rateType.setRateTypeId(rs.getInt(1));
                rateType.setRateName(rs.getString(2));

                rateTypes.add(rateType);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when loading Rate Types: " + e);
        }
        return rateTypes;
    }

    @Override
    public List<Room> getAvailableRoomsByRoomTypeAndDates(int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate) throws DAOException {
        List<Room> rooms = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_AVAILABLE_ROOMS);
            statement.setInt(1, roomTypeId);
            statement.setInt(2, rateTypeId);
            statement.setDate(3, Date.valueOf(checkinDate));
            statement.setDate(4, Date.valueOf(checkoutDate));
            ResultSet rs = statement.executeQuery();

            rooms = new ArrayList<Room>();
            while (rs.next()) {
                Room room = new Room();
                room.setRoomId(rs.getInt(1));
                room.setName(rs.getString(2));
                room.setPrice(rs.getInt(3));

                rooms.add(room);
            }
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when loading rooms: " + e);
        }
        return rooms;
    }


    @Override
    public List<Room> getRooms() throws DAOException {
        List<Room> rooms = null;
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {

            PreparedStatement statement = connection.prepareStatement(SELECT_ROOMS);
            ResultSet rs = statement.executeQuery();

            rooms = new ArrayList<Room>();
            Room room;
            while (rs.next()) {
                room = new Room();
                room.setName(rs.getString(18));
                room.setRoomId(rs.getInt(8));


                RoomType roomType = new RoomType();
                roomType.setName(rs.getString(5));
                room.setRoomType(roomType);

                rooms.add(room);


            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error. Impossible to load rooms: " + e);
        }
        return rooms;
    }

    @Override
    public void delete(int roomid) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            PreparedStatement statement = connection.prepareStatement(DELETE_ROOM);
            statement.setInt(1, roomid);
            int result = statement.executeUpdate();
            statement.closeOnCompletion();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when deleting a room: " + e);
        }
    }

    @Override
    public void create(String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Room room = new Room();

            PreparedStatement statement = connection.prepareStatement(INSERT_ROOM);
            statement.setDate(1, Date.valueOf(checkInDate));
            statement.setDate(2, Date.valueOf(checkOutDate));
            statement.setInt(3, adultsCount);
            statement.setInt(4, 0);
            statement.setString(5, "");
            statement.setInt(6, 1);
            statement.setInt(7, 1);
            statement.setInt(8, 1);

            int result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when creating a room: " + e);
        }
    }

    @Override
    public int update(int roomid, LocalDate checkInDate, LocalDate checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            Room room = new Room();

            PreparedStatement statement = connection.prepareStatement(UPDATE_ROOM);
            statement.setDate(1, Date.valueOf(checkInDate));
            statement.setDate(2, Date.valueOf(checkOutDate));
            statement.setInt(3, adultsCount);
            statement.setInt(4, 0);
            statement.setString(5, "");
            statement.setInt(6, newStatus);
            statement.setInt(7, 1);
            statement.setInt(8, 1);
            statement.setInt(9, roomid);

            int result = statement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
            throw new DAOException("Error when updating a room: " + e);
        }
        return roomid;
    }

}
