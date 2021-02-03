package com.epam.jwd.finaltask.filter;

import com.epam.jwd.finaltask.command.impl.Attributes;
import com.epam.jwd.finaltask.model.Booking;
import com.epam.jwd.finaltask.model.Guest;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IBookingService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.BookingServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@WebFilter(filterName = "BookingsFilter", urlPatterns = {"/views/bookings.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class BookingsFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        IBookingService bookingService = new BookingServiceImpl();
        User loggeduser = (User) request.getSession().getAttribute(Attributes.LOGGEDUSER);
        List<Booking> bookings;
        if (loggeduser.getUserRole().getRoleId() == 2) {
            bookings = bookingService.getAllBookings();
        } else {
            IUserService userService = new UserServiceImpl();
            Guest guest = userService.getGuestByUserId(loggeduser.getUserId());
            bookings = bookingService.getBookingsByGuestId(guest.getGuestId());
        }
        request.setAttribute(Attributes.BOOKINGS, bookings);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
