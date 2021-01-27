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

public class BookingServiceImpl implements IBookingService {
    static final Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
    private IBookingDao bookingDao = new BookingDaoImpl();

    @Override
    public List<Booking> getBookings() {
        try {
            return bookingDao.getBookings();
        } catch (DAOException e) {
            logger.error("Error when calling the method getBookings(): " +e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking getBookingById(int bookingId) {
        try {
            List<Booking> bookings = bookingDao.getBookings();
            for(Booking booking : bookings) {
                if (booking.getBookingId() == bookingId) {
                    return booking;
                }
            }
        } catch (DAOException e) {
            logger.error("Error when calling the method getBookingById(): " +e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Booking getEmptyBooking() {
        Booking booking = new Booking();
        Room room = new Room();
        booking.setOfferedRoom(room);

        RoomType roomType = new RoomType();
        booking.setRoomType(roomType);

        Guest guest = new Guest();
        booking.setGuest(guest);

        BookingStatus bookingStatus = new BookingStatus();
        bookingStatus.setBookingStatusId(-1);
        booking.setBookingStatus(bookingStatus);

        PaymentInfo paymentInfo = new PaymentInfo();
        booking.setPaymentInfo(paymentInfo);

        PaymentMethod paymentMethod = new PaymentMethod();
        booking.setSelectedPaymentMethod(paymentMethod);
        return booking;
    }

    @Override
    public void create(String checkInDate, String checkOutDate, int adultsCount, int childrenCount, String comment, int roomTypeId, int guestId) {
        try {
            if (Date.valueOf(checkInDate).after(Date.valueOf(checkOutDate))) {
                throw new ValidationError(ValidationError.CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE);
            }
            bookingDao.create(checkInDate, checkOutDate, adultsCount, childrenCount, comment, roomTypeId, guestId);
        } catch (DAOException e) {
            logger.error("Error when calling the method update(): " +e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void update(int bookingid, String checkInDate, String checkOutDate, int adultsCount, String guestName, String guestEmail,
                       String guestMobile, String guestAddress, int newStatus) throws ValidationError {
        try {

            if (Date.valueOf(checkInDate).after(Date.valueOf(checkOutDate))) {
                throw new ValidationError(ValidationError.CHECKIN_DATE_HAS_TO_BE_BEFORE_CHECKOUT_DATE);
            }

            bookingDao.update(bookingid, checkInDate, checkOutDate, adultsCount, guestName, guestEmail,
                    guestMobile, guestAddress, newStatus);
        } catch (DAOException e) {
            logger.error("Error when calling the method update(): " +e.getMessage());
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int bookingid) {
        try {
            bookingDao.delete(bookingid);
        } catch (DAOException e) {
            logger.error("Error when calling the method delete(): " +e.getMessage());
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        IBookingService bookingService = new BookingServiceImpl();
        List<Booking> bookings = bookingService.getBookings();

        for (Booking booking : bookings) {
            logger.debug(booking.toString());
            //System.out.println(booking);
        }

    }


}
