package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserCommand extends AbstractCommand {
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String mode = request.getParameter("mode");
        if (request.getParameter("userid") == null) {
            request.setAttribute("mode", mode);
            return Pages.USERDETAILS;
        }

        try {
            String name = request.getParameter("guestName");
            String password = request.getParameter("guestPassword");
            int userRoleId = Integer.parseInt(request.getParameter("userRole"));

            if (mode.equals("create")) {
                userService.create(name, password, userRoleId);
            } else if (mode.equals("update")) {
                int userId = Integer.parseInt(request.getParameter("userid"));
                userService.update(userId, name, password, userRoleId);
            }
            return Pages.USERS;
        } catch (ValidationError ve) {
            request.setAttribute("error", ve.getErrorCode());
            return Pages.USERDETAILS;
        }
    }
}
