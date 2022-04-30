package com.epam.jwd.finaltask.dao.impl;


import com.epam.jwd.finaltask.dao.IRoomPriorityDao;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RoomPriority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class RoomPriorityDaoImpl implements IRoomPriorityDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(com.epam.jwd.finaltask.dao.impl.BookingDaoImpl.class);

    private static final String DELETE_ROOM_PRIRITIES = "DELETE FROM `web`.`roompriority`";

    private static final String RECALCULATE_ROOM_PRIORITY = "INSERT INTO `web`.`roompriority` (`guesttype`, `roomId`, " +
            "`priority`) VALUES (?, ?, ?)";


    @Override
    public void recalculateRoomPriority(List<RoomPriority> roomPriorityList) throws DAOException {
        try (Connection connection = ConnectionPool.getInstance().getConnection()) {
            try (PreparedStatement statement = connection.prepareStatement(DELETE_ROOM_PRIRITIES)) {
                statement.executeUpdate();
                statement.closeOnCompletion();
            }

            try (PreparedStatement statement = connection.prepareStatement(RECALCULATE_ROOM_PRIORITY)) {
                for (RoomPriority roomPriority : roomPriorityList) {
                    statement.setInt(1, roomPriority.getGuesttypeId());
                    statement.setInt(2, roomPriority.getRoomId());
                    statement.setDouble(3, roomPriority.getPriority());
                    statement.executeUpdate();
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage());
            throw new DAOException(e.getMessage());
        }
    }


}

