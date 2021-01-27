package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteUserCommand extends AbstractCommand {

    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        int userId = Integer.parseInt(request.getParameter("userid"));
        userService.delete(userId);
        return Pages.USERS;
    }
}
