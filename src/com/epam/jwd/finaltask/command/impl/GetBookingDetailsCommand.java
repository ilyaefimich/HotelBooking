package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.*;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class GetBookingDetailsCommand extends AbstractCommand {
    private IBookingService bookingService = new BookingServiceImpl();
    private IRoomService roomService = new RoomServiceImpl();
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String mode = request.getParameter("mode");
        if (mode.equals("create")) {
            User loggeduser = (User) request.getSession().getAttribute(Attributes.LOGGEDUSER);
            Guest guest = userService.getGuestByUserId(loggeduser.getUserId());
            Booking booking = new Booking();
            booking.setCheckInDate(LocalDate.now());
            booking.setCheckOutDate(LocalDate.now().plusDays(1));
            booking.setChildrenCount(0);
            booking.setAdultsCount(1);
            List<RateType> rateTypes = roomService.getRateTypesByRoomType(1);
            request.setAttribute("rateTypes", rateTypes);
            request.setAttribute("guest", guest);
            request.setAttribute(Attributes.MODE, "create");
            request.setAttribute(Attributes.BOOKING, booking);

        } else if (mode.equals("update")) {
            int bookingId = Integer.parseInt(request.getParameter("bookingid"));
            Booking booking = bookingService.getBookingById(bookingId);
            List<Room> rooms = roomService.getAvailableRoomsByRoomTypeAndDates(booking.getGuestType().getGuestTypeId(), booking.getRoomType().getRoomTypeId(), booking.getRateType().getRateTypeId(), booking.getCheckInDate().toString(), booking.getCheckOutDate().toString());
            List<RateType> rateTypes = roomService.getRateTypesByRoomType(1);

            request.setAttribute("rooms", rooms);
            request.setAttribute(Attributes.MODE, "update");
            request.setAttribute("rateTypes", rateTypes);
            request.setAttribute(Attributes.BOOKING, booking);

        } else {
            throw new IllegalArgumentException();
        }
        return Pages.BOOKINGDETAILS;

    }
}
