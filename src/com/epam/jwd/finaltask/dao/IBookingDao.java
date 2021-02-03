package com.epam.jwd.finaltask.dao;

import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Booking;

import java.util.List;

public interface IBookingDao {
    List<Booking> getBookings() throws DAOException;
    void delete(int bookingid) throws DAOException;
    void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId, int rateTypeId) throws DAOException;
    void update(int bookingid, Integer roomId, int newStatus, Integer price, String cardholderName, String cardNumber, String ccvCode, String expirationDate, int paymentMethodId) throws DAOException;
}

