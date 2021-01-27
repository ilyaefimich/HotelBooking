package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetAvailableRoomCommand extends AbstractCommand {
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        List<Room> room = roomService.getRooms();
        request.setAttribute(Attributes.ROOMS, room);
        return Pages.ROOMS;

    }
}
