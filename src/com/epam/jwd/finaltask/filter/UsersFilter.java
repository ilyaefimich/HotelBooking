package com.epam.jwd.finaltask.filter;

import com.epam.jwd.finaltask.command.impl.Attributes;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@WebFilter(filterName = "UsersFilter", urlPatterns = {"/views/users.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class UsersFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        IUserService userService = new UserServiceImpl();
        List<User> users = userService.getUsers();
        request.setAttribute(Attributes.USERS, users);
        request.getSession().setAttribute(Attributes.USERS, users);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
