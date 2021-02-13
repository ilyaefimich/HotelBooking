package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;
import com.epam.jwd.finaltask.model.Room;
import com.epam.jwd.finaltask.model.RoomStatus;
import com.epam.jwd.finaltask.model.RoomType;
import com.epam.jwd.finaltask.service.IRoomService;
import com.epam.jwd.finaltask.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetRoomDetailsCommand extends AbstractCommand {
    private IRoomService roomService = new RoomServiceImpl();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<RoomType> roomTypes = roomService.getRoomTypes();
        List<RoomStatus> roomStatuses = roomService.getRoomStatuses();
        request.setAttribute("roomTypes", roomTypes);
        request.setAttribute("roomStatuses", roomStatuses);
        String mode = request.getParameter("mode");
        if (mode.equals("create")) {
            request.setAttribute(Attributes.MODE, "create");
        } else if (mode.equals("update")) {
            int roomId = Integer.parseInt(request.getParameter("roomid"));
            Room room = roomService.getRoomById(roomId);
            request.setAttribute(Attributes.ROOM, room);
            request.setAttribute(Attributes.MODE, "update");
        } else {
            throw new IllegalArgumentException();
        }
        return Pages.ROOMDETAILS;

    }
}
