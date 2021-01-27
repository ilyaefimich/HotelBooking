package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBookingCommand extends AbstractCommand {
    private IBookingService bookingService = new BookingServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        boolean isValid = true;

        String mode = request.getParameter("mode");
        String checkin = request.getParameter("checkin");
        String checkout = request.getParameter("checkout");
        int adultsCount = Integer.parseInt(request.getParameter("adults"));
        int childrenCount = Integer.parseInt(request.getParameter("children"));
        String comment = request.getParameter("comment");
        int guestId = Integer.parseInt(request.getParameter("guestid"));
//TODO add comment, room, payment info,
        try {

            if (mode.equals("create")) {
                int roomTypeId = Integer.parseInt(request.getParameter("roomtype"));
                bookingService.create(checkin, checkout, adultsCount, childrenCount, comment, roomTypeId, guestId);
            } else if (mode.equals("update")) {
                int newStatus = Integer.parseInt(request.getParameter("newStatus"));
                int bookingId = Integer.parseInt(request.getParameter("bookingid"));
                bookingService.update(bookingId, checkin, checkout, adultsCount, null, null, null, null, newStatus);
            }
            return Pages.GETBOOKINGS;
        } catch (ValidationError ve) {
            request.setAttribute("error", ve.getErrorCode());
            return Pages.BOOKINGDETAILS;
        }
    }
}
