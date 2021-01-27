package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteBookingCommand extends AbstractCommand {

    private IBookingService bookingService = new BookingServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int bookingid  = Integer.parseInt(request.getParameter("bookingid"));
        bookingService.delete(bookingid);
        return Pages.GETBOOKINGS;
    }
}
