package com.epam.jwd.finaltask.controller;


import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.CommandFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commandName = req.getParameter("command");
        AbstractCommand command = CommandFactory.valueOf(commandName.toUpperCase()).createCommand();
        String page = command.execute(req,resp);
        if(page != null) {
            getServletContext().getRequestDispatcher(page).forward(req, resp);
        }
    }
}
