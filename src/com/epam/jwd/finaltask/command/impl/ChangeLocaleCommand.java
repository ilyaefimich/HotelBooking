package com.epam.jwd.finaltask.command.impl;

import com.epam.jwd.finaltask.command.AbstractCommand;
import com.epam.jwd.finaltask.command.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

public class ChangeLocaleCommand extends AbstractCommand {

    private static final String RUS = "rus";
    private static final String ENG = "eng";
    private static final String BEL = "bel";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String lang = request.getParameter("lang");
        if (lang.equalsIgnoreCase(RUS)) {
            request.getSession().setAttribute("locale", new Locale("ru","RU"));
        } else if (lang.equalsIgnoreCase(ENG)) {
            request.getSession().setAttribute("locale", new Locale("en","EN"));
        } else if (lang.equalsIgnoreCase(BEL)) {
            request.getSession().setAttribute("locale", new Locale("be","BY"));
        } else {
            throw new IllegalArgumentException("Such a language is not supported! lang=" + lang);
        }
        return Pages.GETBOOKINGS;
    }
}
