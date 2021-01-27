<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script data-require="bootstrap@*" data-semver="3.1.1"
            src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link data-require="bootstrap-css@3.1.1" data-semver="3.1.1" rel="stylesheet"
          href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css"/>

</head>
<body>
<jsp:include page="/views/header.jsp"/>

<form method="POST" action="./controller?command=getrooms">
    <input type="submit" value="<fmt:message key="page.getRooms"/>" class="btn btn-primary btn-xs"/>
</form>

<div class="container-fluid"
     style="padding-left: 30rem; padding-right: 20rem; padding-top: 10rem; padding-bottom: 10rem ">
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th><fmt:message key="page.Room_id"/></th>
            <th><fmt:message key="page.Room_name"/></th>
            <th><fmt:message key="page.RoomType_name"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="room" items="${room}" varStatus="index">
            <tr>

                <td><c:out value="${room.roomId}"/></td>
                <td><c:out value="${room.name}"/></td>
                <td><c:out value="${room.getRoomType().name}"/></td>

                <td>
                    <form method="POST" action="./controller?command=getAvailableRoom">
                        <input type="hidden" name="roomid" value="${room.roomId}"/>
                        <input type="submit" value="<fmt:message key="page.edit"/>" class="btn btn-primary btn-xs"/>
                    </form>
                </td>
                <td>
                    <a href="#confirm-delete" data-href="./controller?command=deleteUser&userid=${user.userId}"
                       data-toggle="modal" class="btn btn-primary btn-xs" data-target="#confirm-delete"><fmt:message
                            key="page.delete"/></a>
                </td>
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
                <p>You are about to delete the record, this procedure is irreversible.</p>
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

</body>
</html>
