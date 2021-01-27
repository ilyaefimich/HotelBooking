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
<jsp:include page="/views/header.jsp"/>


<c:if test="${mode == \"update\" }">
<form class="form-inline needs-validation" method="post" action="./controller?command=updateuser&mode=update">
    </c:if>

    <c:if test="${mode == \"create\" }">
    <form class="form-inline needs-validation" method="post" action="./controller?command=updateuser&mode=create">
        </c:if>

        <div class="col-md-8 order-md-1"
             style="padding-left: 30rem; padding-right: 10rem; padding-top: 10rem; padding-bottom: 10rem">
            <div class="block">

                <input name="userid" type="number" class="form-control" id="userid"
                       value="${user.userId}"
                       hidden="true">

                <div class="col-md-6 mb-3">
                    <label for="guestName">Full name</label>
                    <input name="guestName" type="text" class="form-control" id="guestName" placeholder="John Smith"
                           required="required"
                           value="${user.name}">
                    <div class="invalid-feedback">
                        Valid guest name is required.
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="guestPassword">Password</label>
                    <input name="guestPassword" type="text" class="form-control" id="guestPassword" required="required"
                           value="${user.password}">
                    <div class="invalid-feedback">
                        Valid password is required.
                    </div>
                </div>
                <div class="col-md-5 mb-3">
                    <label for="userRole">User Role</label>
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
                        Please select a valid city.
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="guestMobile">Mobile</label>
                    <input type="text" class="form-control" id="guestMobile" placeholder="+71028493028"
                           required="required"
                           value="TODO">
                    <div class="invalid-feedback">
                        Valid guest's mobile phone number is required.
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email">Email <span class="text-muted">(Optional)</span></label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com"
                           value="TODO">
                    <div class="invalid-feedback">
                        Please enter a valid email address.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" placeholder="1234 Main St"
                           value="TODO">
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>
                <div class="col-md-5 mb-3">
                    <label for="city">City</label>
                    <select class="form-select" id="city">
                        <option value="" selected="selected">Choose...</option>
                        <option>Minsk</option>
                    </select>
                    <div class="invalid-feedback">
                        Please select a valid city.
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-5 mb-3">
                        <label for="country">Country</label>
                        <select class="form-select" id="country" <c:out value="${isDisabled}"/>>
                            <option value="" selected="selected">Choose...</option>
                            <option>Belarus</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid country.
                        </div>
                    </div>
                    <div class="col-md-4 mb-3">
                        <label for="state">State</label>
                        <select class="form-select" id="state">
                            <option value="" selected="selected">Choose...</option>
                            <option>Minsk</option>
                        </select>
                        <div class="invalid-feedback">
                            Please provide a valid state.
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="zip">Zip</label>
                        <input type="text" class="form-select" id="zip" placeholder="">
                        <div class="invalid-feedback">
                            Zip code required.
                        </div>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary btn-lg btn-block" type="submit"
                    value="<fmt:message key="page.save"/>"></button>
            <a href="./controller?command=getusers" id="cancel" name="cancel"
               class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
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
