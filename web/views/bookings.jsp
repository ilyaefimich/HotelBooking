<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Bookings</title>

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/views/header.jsp"/>


<div class="container-fluid"
     style="padding-left: 30rem; padding-right: 20rem; padding-top: 10rem; padding-bottom: 10rem ">

    <form method="POST" action="./controller?command=bookingdetails&mode=create">
        <div class="row">
            <div class="col-xs-12">
                <div class="text-right">
                    <input type="submit" value="<fmt:message key="page.add_booking"/>"
                           class="btn btn-primary btn-xs pull-right"/>
                </div>
            </div>
        </div>
    </form>

    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th><fmt:message key="page.booking_id"/></th>
            <th><fmt:message key="page.booking_checkin"/></th>
            <th><fmt:message key="page.booking_checkout"/></th>
            <th><fmt:message key="page.booking_adults"/></th>
            <th><fmt:message key="page.booking_children"/></th>
            <th><fmt:message key="page.booking_room_type"/></th>
            <th><fmt:message key="page.booking_status"/></th>
            <th><fmt:message key="page.booking_created_on"/></th>
            <th><fmt:message key="page.booking_guest_name"/></th>
            <th><fmt:message key="page.booking_guest_address"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="booking" items="${bookings}" varStatus="index">
            <tr>

                <td><c:out value="${booking.bookingId}"/></td>
                <td><c:out value="${booking.checkInDate}"/></td>
                <td><c:out value="${booking.checkOutDate}"/></td>
                <td><c:out value="${booking.adultsCount}"/></td>
                <td><c:out value="${booking.childrenCount}"/></td>
                <td><c:out value="${booking.getRoomType().name}"/></td>
                <td><c:out value="${booking.getBookingStatus().name}"/></td>
                <td><c:out value="${booking.bookingId}"/></td>
                <td><c:out value="${booking.getGuest().name}"/></td>
                <td><c:out value="${booking.getGuest().address}"/></td>

                <td>
                    <form method="POST" action="./controller?command=bookingdetails&mode=update">
                        <input type="hidden" name="bookingid" value="${booking.bookingId}"/>
                        <input type="submit" value="<fmt:message key="page.edit"/>" class="btn btn-primary btn-xs"/>
                    </form>
                </td>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <td>
                        <a href="#confirm-delete"
                           data-href="./controller?command=deletebooking&bookingid=${booking.bookingId}"
                           data-toggle="modal" class="btn btn-primary btn-xs" data-target="#confirm-delete"><fmt:message
                                key="page.delete"/></a>
                    </td>
                </c:if>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="modal fade" id="confirm-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">
    <div class="modal-dialog modal-dialog-centered">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">Confirm Delete</h4>
            </div>

            <div class="modal-body">
                <p>You are about to delete one track, this procedure is irreversible.</p>
                <p>Do you want to proceed?</p>
                <p class="debug-url"></p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
                <a class="btn btn-danger btn-ok">Delete</a>
            </div>
        </div>
    </div>
</div>

<script>

    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });

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
