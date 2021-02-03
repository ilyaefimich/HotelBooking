package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetBookingsCommand extends AbstractCommand {
    private IBookingService bookingService = new BookingServiceImpl();
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User loggeduser = (User) request.getSession().getAttribute(Attributes.LOGGEDUSER);
        List<Booking> bookings;
        if (loggeduser.getUserRole().getRoleId() == 2) {
            bookings = bookingService.getAllBookings();
        } else {
            Guest guest = userService.getGuestByUserId(loggeduser.getUserId());
            bookings = bookingService.getBookingsByGuestId(guest.getGuestId());
        }
        request.setAttribute(Attributes.BOOKINGS, bookings);
        return Pages.GETBOOKINGS;
    }
}
