package com.epam.jwd.finaltask.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * All types of commands have to implement this method execute to be invoked in controller servlet
 */
public abstract class AbstractCommand {
    /**
     *
     * @param request a request from client
     * @param response a response from server
     * @return the URL (web, jsp page) which will be used in controller to forward to
     */
    public abstract String execute(HttpServletRequest request, HttpServletResponse response) ;

}
