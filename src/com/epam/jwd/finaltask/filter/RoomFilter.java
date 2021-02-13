package com.epam.jwd.finaltask.filter;

import com.epam.jwd.finaltask.command.impl.Attributes;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;


@WebFilter(filterName = "RoomFilter", urlPatterns = {"/views/rooms.jsp"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class RoomFilter implements Filter {



    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        IRoomService roomService = new RoomServiceImpl();
        List<Room> rooms = roomService.getAllRooms();
        request.setAttribute(Attributes.ROOMS, rooms);
        request.getSession().setAttribute(Attributes.ROOMS, rooms);
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
