package com.epam.jwd.finaltask.filter;

import com.epam.jwd.finaltask.command.impl.Attributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * LoginFilter checks what for all request for user validates that user has been logged in if not then forwards to login page
 */
@WebFilter(filterName = "LoginFilter", urlPatterns = {"/*"}, dispatcherTypes = {DispatcherType.FORWARD, DispatcherType.REQUEST})
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String param = request.getParameter("command");
        if (param != null && (param.equals("login") || param.equals("signup"))) {
            filterChain.doFilter(request, response);
            return;
        }

        HttpSession session = request.getSession(false);
        String loginURI = request.getContextPath() + "/controller?command=login";

        boolean loggedIn = session != null && session.getAttribute(Attributes.LOGGEDUSER) != null;
        boolean loginRequest = request.getRequestURI().equals(loginURI);

        if (loggedIn || loginRequest) {
            filterChain.doFilter(request, response);
        } else {
            response.sendRedirect(loginURI);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
