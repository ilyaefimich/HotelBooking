package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.List;

public class GetBookingDetailsCommand extends AbstractCommand {


    private IBookingService bookingService = new BookingServiceImpl();
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String mode = request.getParameter("mode");
        if (mode.equals("create")) {
            //Booking booking = bookingService.getEmptyBooking();
            //request.setAttribute(Attributes.BOOKING, booking);
            request.setAttribute(Attributes.MODE, "create");
        } else if (mode.equals("update")) {
            int bookingId = Integer.parseInt(request.getParameter("bookingid"));
            Booking booking = bookingService.getBookingById(bookingId);
            request.setAttribute(Attributes.BOOKING, booking);

            List<Room> rooms = roomService.getAvailableRoomsByRoomTypeAndDates(booking.getRoomType().getRoomTypeId(), booking.getCheckInDate().toString(), booking.getCheckOutDate().toString());
            request.setAttribute("rooms", rooms);
            request.setAttribute(Attributes.MODE, "update");
        } else {
            throw new IllegalArgumentException();
        }
        return Pages.BOOKINGDETAILS;

    }
}
