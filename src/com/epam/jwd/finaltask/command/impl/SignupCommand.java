package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class SignupCommand extends AbstractCommand {
    private IUserService userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        if (request.getParameter("userid") == null) {
            request.setAttribute("mode", "signup");
            return Pages.USERDETAILS;
        } else {
            try {
                String login = request.getParameter("loginname");
                String password = request.getParameter("userPassword");
                String guestName = request.getParameter("guestName");
                String mobile = request.getParameter("mobile");
                String email = request.getParameter("email");
                String address = request.getParameter("address");

                userService.create(login, password, 1, guestName, mobile, email, address);
                User user = userService.getUserByNameAndPassword(login, password);
                request.getSession().setAttribute("locale", new Locale("en","EN"));

                if (user != null) {
                    request.getSession().setAttribute(Attributes.LOGGEDUSER, user);
                    return Pages.GETBOOKINGS;

                } else if (login != null || password != null){
                    request.setAttribute("error", "Incorrect user name or password, please, try again.");
                    return Pages.LOGIN;
                } else {
                    return Pages.LOGIN;
                }

            } catch (ValidationError ve) {
                request.setAttribute("error", ve.getErrorCode());
                return Pages.USERDETAILS;
            }

        }

    }
}
