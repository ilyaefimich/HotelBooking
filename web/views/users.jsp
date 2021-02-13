<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Users</title>
    <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
            integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
          integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>
<jsp:include page="/views/header.jsp"/>

<div class="container-fluid" style="padding-left: 30rem; padding-right: 20rem; padding-top: 10rem; padding-bottom: 10rem ">
    <form method="POST" action="./controller?command=updateuser&mode=create">
        <div class="row">
            <div class="col-xs-12">
                <div class="text-right">
                    <input type="submit" value="<fmt:message key="page.add_User"/>"
                           class="btn btn-primary btn-xs pull-right"/>
                </div>
            </div>
        </div>
    </form>
    <table class="table">
        <thead class="thead-inverse">
        <tr>
            <th><fmt:message key="page.User_id"/></th>
            <th><fmt:message key="page.User_name"/></th>
            <th><fmt:message key="page.User_role"/></th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="user" items="${users}" varStatus="index">
            <tr>

                <td><c:out value="${user.userId}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.getUserRole().roleName}"/></td>

                <td>
                    <form method="POST" action="./controller?command=getuserdetails&mode=update">
                        <input type="hidden" name="userid" value="${user.userId}"/>
                        <input type="submit" value="<fmt:message key="page.edit"/>" class="btn btn-primary btn-xs"/>
                    </form>
                </td>
                <td>
                    <a href="#confirm-delete" data-href="./controller?command=deleteUser&userid=${user.userId}"
                       data-toggle="modal" class="btn btn-primary btn-xs" data-target="#confirm-delete"><fmt:message key="page.delete"/></a>
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

</script>
</body>
</html>
