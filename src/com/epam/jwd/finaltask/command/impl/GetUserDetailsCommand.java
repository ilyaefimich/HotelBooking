package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetUserDetailsCommand extends AbstractCommand {

    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String mode = request.getParameter("mode");
        if (mode.equals("create")) {
            request.setAttribute(Attributes.MODE, "create");
        } else if (mode.equals("update")) {
            int userId = Integer.parseInt(request.getParameter("userid"));
            User user = userService.getUserById(userId);
            request.setAttribute(Attributes.USER, user);
            request.setAttribute(Attributes.MODE, "update");
        } else {
            throw new IllegalArgumentException();
        }
        return Pages.USERDETAILS;

    }
}
