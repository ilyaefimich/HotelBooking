package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.exception.ValidationError;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateRoomCommand extends AbstractCommand {
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String mode = request.getParameter("mode");
        try {
            if (mode.equals("update")) {
                String roomName = request.getParameter("roomName");
                int roomType = Integer.parseInt(request.getParameter("roomType"));
                int roomStatus = Integer.parseInt(request.getParameter("roomStatus"));
                int roomId = Integer.parseInt(request.getParameter("roomid"));
                roomService.update(roomId, roomName, roomStatus, roomType);

            } else if (mode.equals("create")) {
                String roomName = request.getParameter("roomName");
                int roomType = Integer.parseInt(request.getParameter("roomType"));
                int roomStatus = Integer.parseInt(request.getParameter("roomStatus"));
                roomService.create(roomName, roomStatus, roomType);


            }
        } catch (ValidationError ve) {
            request.setAttribute("error", ve.getErrorCode());


        }
        return Pages.ROOMS;
    }
}
