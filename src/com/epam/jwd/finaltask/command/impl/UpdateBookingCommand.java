package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateBookingCommand extends AbstractCommand {
    private IBookingService bookingService = new BookingServiceImpl();
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String mode = request.getParameter("mode");
        try {
            if (mode.equals("create")) {
                String checkin = request.getParameter("checkin");
                String checkout = request.getParameter("checkout");
                int adultsCount = Integer.parseInt(request.getParameter("adults"));
                int childrenCount = Integer.parseInt(request.getParameter("children"));
                String comment = request.getParameter("comment");
                int userId = Integer.parseInt(request.getParameter("userid"));
                Guest guest = userService.getGuestByUserId(userId);
                int roomTypeId = Integer.parseInt(request.getParameter("roomtype"));
                int rateTypeId = Integer.parseInt(request.getParameter("rateType"));
                //int price = Integer.parseInt(request.getParameter("price"));
                bookingService.create(checkin, checkout, adultsCount, childrenCount, comment, roomTypeId, guest.getGuestId(), rateTypeId);
            } else if (mode.equals("update")) {
                int bookingId = Integer.parseInt(request.getParameter("bookingid"));
                String roomid = request.getParameter("roomid");

                int newStatus = Integer.parseInt(request.getParameter("newStatus"));
                String cardName = request.getParameter("cardName");
                String cardNumber = request.getParameter("cardNumber");
                String expirationDate = request.getParameter("expirationDate");
                String cvvCode = request.getParameter("cvvCode");
                //String paymentMethodId = request.getParameter("paymentMethod"); //TODO save payment params to db and show when create a new booking

                if (roomid != null) {
                    Integer roomId = Integer.parseInt(roomid);
                    int price = Integer.parseInt(request.getParameter("price"));
                    bookingService.update(bookingId, roomId, newStatus, price, cardName, cardNumber, cvvCode, expirationDate, 1);
                } else {
                    bookingService.update(bookingId, null, newStatus, null, cardName, cardNumber, cvvCode, expirationDate, 1);
                }

            }

            return Pages.GETBOOKINGS;
        } catch (
                ValidationError ve) {
            request.setAttribute("error", ve.getErrorDescription());
            return Pages.BOOKINGDETAILS;
        }
    }
}
