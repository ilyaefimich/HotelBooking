package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.RoomPriority;

import java.util.List;

public interface IRoomPriorityDao {
    void recalculateRoomPriority(List<RoomPriority> roomPriorityList) throws DAOException;

}
