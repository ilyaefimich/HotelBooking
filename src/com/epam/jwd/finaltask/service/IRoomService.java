package com.epam.jwd.finaltask.service;

import com.epam.jwd.finaltask.model.RateType;
import com.epam.jwd.finaltask.model.Room;

import java.time.LocalDate;
import java.util.List;

public interface IRoomService {
    List<Room> getRooms();
    List<Room> getAvailableRoomsByRoomTypeAndDates(int roomTypeId, int rateTypeId, String checkinDate, String checkoutDate);
    List<RateType> getRateTypesByRoomType(int roomTypeId);

    int update(int roomid, LocalDate checkInDate, LocalDate checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus);
}
