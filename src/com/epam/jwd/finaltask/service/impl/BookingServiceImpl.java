package com.epam.jwd.finaltask.service.impl;

import com.epam.jwd.finaltask.dao.IBookingDao;
import com.epam.jwd.finaltask.dao.impl.BookingDaoImpl;
import com.epam.jwd.finaltask.exception.DAOException;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.PaymentInfo;
import com.epam.jwd.finaltask.model.PaymentMethod;
import com.epam.jwd.finaltask.service.IBookingService;

import java.util.List;

public class BookingServiceImpl implements IBookingService {
    private IBookingDao bookingDao = new BookingDaoImpl();


    @Override
    public List<Booking> getBookings() {
        try {
            return bookingDao.getBookings();
        } catch (DAOException e) {
            e.printStackTrace();
        }


        return null;
    }


    public static void main(String[] args) {
        IBookingService bookingService = new BookingServiceImpl();
        List<Booking> bookings = bookingService.getBookings();

        for (Booking booking : bookings) {
            System.out.println(booking);
        }

    }


}
