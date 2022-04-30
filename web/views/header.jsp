<%@ page isELIgnored="false" contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<nav class="navbar navbar-right navbar-expand-lg navbar-dark bg-dark" style="background-color: rgba(25,25,25,0.94) !important;">
    <div class="collapse navbar-collapse" id="navbarColor01">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="./index.jsp"><fmt:message key="page.home"/></a>
            </li>

            <li class="nav-item">
                <a class="nav-link" href="./controller?command=getbookings"><fmt:message key="page.bookings"/></a>
            </li>
            <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                <li class="nav-item">
                    <a class="nav-link" href="./controller?command=getusers"><fmt:message key="page.users"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./controller?command=getrooms"><fmt:message key="page.rooms"/></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="./views/roompriorities.jsp"><fmt:message key="page.roompriorities"/></a>
                </li>
            </c:if>
        </ul>
        <form class="form-inline">
            <ul class="nav navbar-nav navbar-right  my-2 my-lg-0">
                <c:if test="${!loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <li class="nav-item">
                        <a class="nav-link" href="./controller?command=getprofile"><fmt:message
                                key="page.my_profile"/></a>
                    </li>
                </c:if>


                <li class="nav-item">
                    <div class="dropdown">
                        <button class="nav-link btn btn-outline-info my-2 my-sm-0 btn-secondary dropdown-toggle"
                                type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true"
                                aria-expanded="false">
                            ${locale.getDisplayLanguage(locale)}
                        </button>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
                            <a class="dropdown-item" href="./controller?command=changelocale&lang=rus">русский</a><br>
                            <a class="dropdown-item" href="./controller?command=changelocale&lang=eng">english</a><br>
                            <a class="dropdown-item" href="./controller?command=changelocale&lang=bel">беларускi</a><br>
                        </div>
                    </div>
                </li>

                <c:if test="${loggeduser!=null}">
                    <li class="nav-item">
                        <a class="nav-link  btn btn-outline-info my-2 my-sm-0"
                           href="./controller?command=logout"><fmt:message key="navbar.logout"/></a>
                    </li>
                </c:if>
            </ul>
        </form>
    </div>
</nav>

