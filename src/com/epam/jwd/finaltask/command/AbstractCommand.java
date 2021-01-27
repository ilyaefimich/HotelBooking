package com.epam.jwd.finaltask.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractCommand {

    public abstract String execute(HttpServletRequest request, HttpServletResponse response) ;

}
