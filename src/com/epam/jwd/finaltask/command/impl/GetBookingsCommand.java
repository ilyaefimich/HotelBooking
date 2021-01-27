package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBookingsCommand extends AbstractCommand {


    private IBookingService bookingService = new BookingServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Booking> bookings = bookingService.getBookings();
        request.setAttribute(Attributes.BOOKINGS, bookings);
        return Pages.BOOKINGS;

    }
}
