package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class LoginCommand extends AbstractCommand {

    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("user-login");
        String password = request.getParameter("user-password");
        User user = userService.getUserByNameAndPassword(login, password);
        request.getSession().setAttribute("locale", new Locale("en", "EN"));
        if (user != null) {
            request.getSession().setAttribute(Attributes.LOGGEDUSER, user);
            return Pages.HOME;

        } else if (login != null || password != null) {
            request.setAttribute("error", "Incorrect user name or password, please, try again.");
            return Pages.LOGIN;
        } else {
            return Pages.LOGIN;
        }
    }
}
