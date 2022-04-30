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
    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="./js/table2CSV.js"/>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

    <%--<script src="./js/table2excel.js"/>--%>

</head>
<body>
<jsp:include page="/views/header.jsp"/>

<div class="container-fluid"
     style="padding-left: 30rem; padding-right: 20rem; padding-bottom: 10rem ">

    <h1 align="center"><fmt:message key="page.bookings"/></h1>

    <form method="POST" action="./controller?command=bookingdetails&mode=create">
        <div class="row text-right">
            <div class="col-xs-12">
                <input type="submit" value="<fmt:message key="page.add_booking"/>"
                       class="btn btn-primary btn-xs pull-right"/>
            </div>

        </div>
    </form>

    <input class="form-control" id="bookingsSearch" type="text" placeholder="Search..">
    <br>

    <table id="bookingsTable" class="table">
        <tr>
            <th><fmt:message key="page.booking_id"/></th>O
            <th><fmt:message key="page.booking_checkin"/></th>
            <th><fmt:message key="page.booking_checkout"/></th>
            <th><fmt:message key="page.booking_adults"/></th>
            <th><fmt:message key="page.booking_children"/></th>
            <th><fmt:message key="page.booking_room_type"/></th>
            <th><fmt:message key="page.booking_status"/></th>
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
                <h4 class="modal-title" id="myModalLabel"><fmt:message key="page.confirmDelete"/></h4>
            </div>

            <div class="modal-body">
                <p><fmt:message key="page.messageBeforeDelete1"/></p>
                <p><fmt:message key="page.messageBeforeDelete2"/></p>
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal"><fmt:message key="page.cancel"/></button>
                <a class="btn btn-danger btn-ok"><fmt:message key="page.delete"/></a>
            </div>
        </div>
    </div>
</div>

<script>

    $('#confirm-delete').on('show.bs.modal', function (e) {
        $(this).find('.btn-ok').attr('href', $(e.relatedTarget).data('href'));

        $('.debug-url').html('Delete URL: <strong>' + $(this).find('.btn-ok').attr('href') + '</strong>');
    });

    $(document).ready(function () {
        /*var table = $('#bookingsTable').DataTable();*/
      /*  var $table1 = $('#bookingsTable');
        $('#btn-export').click(function() {
            $table1.tableExport({
                type: 'csv',
                escape: false,
                exportDataType: 'all',
                refreshOptions: {
                    exportDataType: 'all'
                }
            });
        });

        $('#btn-export1').on('click', function(){
            $('<table>').append(table.$('tr').clone()).table2excel({
                exclude: ".excludeThisClass",
                name: "Bookings export",
                filename: "BookingsExport" //do not include extension
            });
        });
*/
        $("#bookingsSearch").on("keyup", function () {
            var value = $(this).val().toLowerCase();
            $("#bookingsTable tr").filter(function () {
                $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
            });
        });

      //  $('#bookingsTable').DataTable();
    });

    $(document).ready(function () {
        $('table').each(function () {
            var $table = $(this);

            var $button = $("<button type='button'>");
            $button.text("Excel");
            $button.insertAfter($table);

            $button.click(function () {
                var csv = $table.table2CSV({
                    delivery: 'value'
                });
                window.location.href = 'data:text/csv;charset=UTF-8,'
                    + encodeURIComponent(csv);
            });
        });
    })
</script>

</body>
</html>
