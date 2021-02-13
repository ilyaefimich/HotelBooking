package com.epam.jwd.finaltask.service.impl;

import com.epam.jwd.finaltask.dao.IBookingDao;
import com.epam.jwd.finaltask.dao.impl.BookingDaoImpl;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.*;
import com.epam.jwd.finaltask.service.IBookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

public class BookingServiceImpl implements IBookingService {
    static final Logger LOGGER = LoggerFactory.getLogger(BookingServiceImpl.class);
    private IBookingDao bookingDao = new BookingDaoImpl();

    @Override
    public List<Booking> getAllBookings() {
        try {
            return bookingDao.getBookings();
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getBookings(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Booking> getBookingsByGuestId(int guestId) {

        try {
            List<Booking> bookings = bookingDao.getBookings();
            bookings = bookings
                    .stream()
                    .filter(Booking -> Booking.getGuest().getGuestId() == guestId)
                    .collect(Collectors.toList());
            return bookings;
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getBookingsByGuestId(): " + e.getMessage());
        }
        return null;
    }

    @Override
    public Booking getBookingById(int bookingId) {
        try {
            List<Booking> bookings = bookingDao.getBookings();
            for (Booking booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    return booking;
                }
            }
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method getBookingById(): " + e.getMessage());
        }
        return null;
    }


    @Override
    public void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId, int rateTypeId) {
        try {
            if (Date.valueOf(checkInDate).after(Date.valueOf(checkOutDate))) {
                throw new ValidationError(ValidationError.CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE);
            }
            bookingDao.create(checkInDate, checkOutDate, adultsCount, childrenCount, comment, roomTypeId, guestId, rateTypeId);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method create(): " + e.getMessage());
        }
    }

    @Override
    public void update(int bookingid, Integer roomId, int newStatus, Integer price, String cardholderName, String cardNumber, String ccvCode, String expirationDate, int paymentMethodId) {
        try {
            bookingDao.update(bookingid, roomId, newStatus, price, cardholderName, cardNumber, ccvCode, expirationDate, paymentMethodId);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method update(): " + e.getMessage());
        }

    }

    @Override
    public void delete(int bookingid) {
        try {
            bookingDao.delete(bookingid);
        } catch (DAOException e) {
            LOGGER.error("Error when calling the method delete(): " + e.getMessage());
        }

    }
}
