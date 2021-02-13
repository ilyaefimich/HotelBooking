package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteRoomCommand extends AbstractCommand {
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int roomid  = Integer.parseInt(request.getParameter("roomid"));
        roomService.delete(roomid);
        return Pages.ROOMS;
    }
}
