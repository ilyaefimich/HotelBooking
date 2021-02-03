package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateProfileCommand extends AbstractCommand {
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            String name = request.getParameter("loginname");
            String password = request.getParameter("userPassword");
            String guestName = request.getParameter("guestName");
            String mobile = request.getParameter("mobile");
            String email = request.getParameter("email");
            String address = request.getParameter("address");

            int userId = Integer.parseInt(request.getParameter("userid"));
            userService.update(userId, name, password, 1, guestName, mobile, email, address);
            User user = userService.getUserById(userId);
            request.getSession().setAttribute(Attributes.LOGGEDUSER, user);

            return Pages.GETBOOKINGS;
        } catch (ValidationError ve) {
            request.setAttribute("error", ve.getErrorCode());
            return Pages.USERDETAILS;
        }
    }
}
