package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetProfileCommand extends AbstractCommand {

    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User loggeduser = (User) request.getSession().getAttribute(Attributes.LOGGEDUSER);
        User user = userService.getUserById(loggeduser.getUserId());
        request.setAttribute(Attributes.USER, user);
        request.setAttribute(Attributes.MODE, "getprofile");
        Guest guest = userService.getGuestByUserId(loggeduser.getUserId());
        request.setAttribute("guest", guest);
        return Pages.USERDETAILS;

    }
}
