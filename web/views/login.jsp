<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vld" uri="/WEB-INF/validation.tld" %>
<%@ page isELIgnored="false" errorPage="error.jsp" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>

    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>

</head>
<body>
<div class="container-fluid">
    <div class="panel panel-success">
        <div class="panel-heading" align="center">
            <h4>Login</h4>
        </div>

        <div class="panel-body" align="center">
            <div class="container " style=" margin-bottom: 10%;">
                <div class="panel panel-success" style="max-width: 35%;" align="left">
                    <div class="panel-body">

                        <vld:error errorMessage="${error}"/>

                        <form name="loginForm" action="/jwd_web_project_war_exploded/controller?command=login"
                              method="post">
                            <div class="form-group">
                                <label><fmt:message key="page.login"/></label> <input
                                    type="text" class="form-control" name="user-login" id="user-name-id"
                                    placeholder="User Name" required="required">
                            </div>
                            <div class="form-group">
                                <label><fmt:message key="page.password"/></label> <input
                                    type="password" class="form-control" name="user-password" id="user-password-id"
                                    placeholder="Password" required="required">
                            </div>
                            <button type="submit" style="width: 100%;"
                                    class="btn btn-large btn btn-success btn-lg btn-block"><b><fmt:message
                                    key="page.enter"/></b></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>