package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.User;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.IUserService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;
import com.epam.jwd.finaltask.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class GetRoomsCommand extends AbstractCommand {
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Room> rooms = roomService.getRooms();
        request.setAttribute(Attributes.ROOMS, rooms);
        return Pages.ROOMS;

    }

}
