<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>User details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">

</head>
<body class="bg-light">

<c:if test="${mode == \"update\" }">
<form class="form-inline needs-validation" method="post" action="./controller?command=updateuser&mode=update">
    </c:if>

    <c:if test="${mode == \"create\" }">
    <form class="form-inline needs-validation" method="post" action="./controller?command=updateuser&mode=create">
        </c:if>

        <c:if test="${(mode == \"signup\")}">
        <form class="form-inline needs-validation" method="post" action="./controller?command=signup">
            </c:if>

            <c:if test="${mode == \"getprofile\" }">
            <form class="form-inline needs-validation" method="post" action="./controller?command=updateprofile">
                </c:if>

                <div class="col-md-8 order-md-1"
                     style="padding-left: 30rem; padding-right: 10rem; padding-top: 10rem; padding-bottom: 10rem">
                    <div class="block">

                        <input name="userid" type="number" class="form-control" id="userid"
                               value="${user.userId}"
                               hidden="true">

                        <div class="col-md-6 mb-3">
                            <label for="loginname"><fmt:message key="page.loginName"/></label>
                            <input name="loginname" type="text" class="form-control" id="loginname"
                                   placeholder="mylogin"
                                   required="required"
                                   value="${user.name}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.validLoginName"/>
                            </div>
                        </div>
                        <div class="col-md-6 mb-3">
                            <label for="userPassword"><fmt:message key="page.password"/></label>
                            <input name="userPassword" type="password" class="form-control" id="userPassword"
                                   required="required"
                                   value="${user.password}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.validPassword"/>
                            </div>
                        </div>

                        <c:if test="${(mode == \"create\" || mode == \"update\")}">
                            <div class="col-md-5 mb-3">
                                <label for="userRole"><fmt:message key="page.User_role"/></label>
                                <select class="form-select" id="userRole" name="userRole">
                                    <option value="1"
                                            <c:if test="${user.getUserRole().roleId == 1}">
                                                selected="selected"
                                            </c:if>
                                    >Guest
                                    </option>
                                    <option value="2"
                                            <c:if test="${user.getUserRole().roleId == 2}">
                                                selected="selected"
                                            </c:if>
                                    >Administrator
                                    </option>
                                </select>
                                <div class="invalid-feedback">
                                    <fmt:message key="page.validRole"/>
                                </div>
                            </div>
                        </c:if>
                        <div class="col-md-6 mb-3">
                            <label for="guestName"><fmt:message key="page.guestName"/></label>
                            <input name="guestName" type="text" class="form-control" id="guestName"
                                   placeholder="John Smith"
                                   required="required"
                                   value="${guest.name}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.validGuestName"/>
                            </div>
                        </div>

                        <div class="col-md-6 mb-3">
                            <label for="mobile"><fmt:message key="page.guestMobile"/></label>
                            <input type="text" class="form-control" name="mobile" id="mobile" placeholder="+71028493028"
                                   required="required"
                                   value="${guest.mobile}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.validGuestMobile"/>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label for="email"><fmt:message key="page.emailAddress"/> <span class="text-muted">(Optional)</span></label>
                            <input type="email" class="form-control" name="email" id="email" placeholder="you@example.com"
                                   value="${guest.email}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.validEmailAddress"/>

                            </div>
                        </div>

                        <div class="mb-3">
                            <label for="address"><fmt:message key="page.address"/></label>
                            <input type="text" class="form-control" id="address" name="address" placeholder="1234 Main St"
                                   value="${guest.address}">
                            <div class="invalid-feedback">
                                <fmt:message key="page.shippingAddress"/>
                            </div>
                        </div>
                    </div>
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message
                            key="page.save"/></button>
                    <c:if test="${mode == \"create\" }">
                        <a href="./controller?command=getusers" id="cancel" name="cancel"
                           class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
                    </c:if>

                    <c:if test="${mode == \"update\" }">
                        <a href="./controller?command=getusers" id="cancel" name="cancel"
                           class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
                    </c:if>

                    <c:if test="${mode == \"signup\" }">
                        <a href="./controller?command=getbookings" id="cancel" name="cancel"
                           class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
                    </c:if>

                    <c:if test="${mode == \"getprofile\" }">
                        <a href="./controller?command=getbookings" id="cancel" name="cancel"
                           class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
                    </c:if>

                </div>
            </form>

            <script>
                // Example starter JavaScript for disabling form submissions if there are invalid fields
                (function () {
                    'use strict'

                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                    var forms = document.querySelectorAll('.needs-validation')

                    // Loop over them and prevent submission
                    Array.prototype.slice.call(forms)
                        .forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                if (!form.checkValidity()) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }

                                form.classList.add('was-validated')
                            }, false)
                        })
                })()
            </script>
            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                    integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
                    crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                    integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                    crossorigin="anonymous"></script>
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                    integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                    crossorigin="anonymous"></script>
</body>
</html>
