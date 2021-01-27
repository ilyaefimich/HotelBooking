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

    <!-- Custom styles for this template -->
    <link href="%D0%9F%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F%20%D1%84%D0%BE%D1%80%D0%BC%D0%B0%20Checkout%20example%20for%20Bootstrap%20(BS%204.0)_files/form-validation.css"
          rel="stylesheet">

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
                        >Guest</option>
                        <option value="2"
                                <c:if test="${user.getUserRole().roleId == 2}">
                                    selected="selected"
                                </c:if>
                        >Administrator</option>
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

</body>
</html>
