<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Room details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">

</head>
<body class="bg-light">
<div class="col-md-8 order-md-1"
     style="padding-left: 30rem; padding-right: 10rem; padding-top: 10rem; padding-bottom: 10rem">
    <vld:error errorMessage="${error}"/>

    <c:if test="${mode == \"update\" }">
    <form class="form-inline needs-validation" method="post" action="./controller?command=updateroom&mode=update">
        </c:if>

        <c:if test="${mode == \"create\" }">
        <form class="form-inline needs-validation" method="post" action="./controller?command=updateroom&mode=create">
            </c:if>

            <div class="col-md-6 mb-3">
                <label for="roomName"><fmt:message key="page.Room_name"/></label>
                <input name="roomName" type="text" class="form-control" id="roomName"
                       placeholder="Room name, e.g. Room 101"
                       required="required"
                       value="${room.name}">
                <input name="roomid" type="text" class="form-control" id="roomid"
                       value="${room.roomId}" hidden="true">
                <div class="invalid-feedback">
                    <fmt:message key="page.validRoomMessage"/>
                </div>
            </div>
            <div class="col-md-5 mb-3">
                <label for="roomType"><fmt:message key="page.Room_Type"/></label>
                <select class="form-select " name="roomType" id="roomType">
                    <c:forEach var="roomType" items="${roomTypes}" varStatus="index">
                        <option value="<c:out value="${roomType.roomTypeId}"/>"
                                <c:if test="${room.getRoomType().roomTypeId == roomType.roomTypeId}">
                                    selected="selected"
                                </c:if>
                        ><c:out value="${roomType.name}"/></option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    <fmt:message key="page.roomTypeMessage"/>
                </div>
            </div>
            <div class="col-md-5 mb-3">
                <label for="roomStatus"><fmt:message key="page.Room_Status"/></label>
                <select class="form-select " name="roomStatus" id="roomStatus">
                    <c:forEach var="roomStatus" items="${roomStatuses}" varStatus="index">
                        <option value="<c:out value="${roomStatus.roomStatusId}"/>"
                                <c:if test="${room.getRoomStatus().roomStatusId == roomStatus.roomStatusId}">
                                    selected="selected"
                                </c:if>
                        ><c:out value="${roomStatus.name}"/></option>
                    </c:forEach>
                </select>
                <div class="invalid-feedback">
                    <fmt:message key="page.roomStatusMessage"/>
                </div>
            </div>

            <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message
                    key="page.save"/></button>
            <a href="./controller?command=getrooms" id="cancel" name="cancel"
               class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
        </form>
</div>
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
