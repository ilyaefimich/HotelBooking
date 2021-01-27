package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.PaymentMethod;

import java.util.List;

public interface IBookingDao {
    List<Booking> getBookings() throws DAOException;

    void delete(int bookingid) throws DAOException;

    void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId) throws DAOException;

    void update(int bookingid, String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail, String guestMobile, String guestAddress, int newStatus) throws DAOException;
}

