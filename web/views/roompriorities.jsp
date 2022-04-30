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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">

</head>
<body>

<div class="container-fluid">
    <div class="panel panel-success">
        <div class="panel-heading" align="center">
            <h4><fmt:message key="page.calculateRoomPriorities"/></h4>
        </div>

        <div class="panel-body" align="center">
            <div class="container " style=" margin-bottom: 10%;">
                <div class="panel panel-success" style="max-width: 35%;" align="left">
                    <div class="panel-body">

                        <vld:error errorMessage="${error}"/>


                        <form name="fileUploadForm" action="../controller?command=calculateroompriority"
                              method="post" enctype="multipart/form-data">
                            <div class="form-group">
                                <label for="file"><fmt:message key="page.selectScvFile"/></label>
                                <input type="file" name="dataFile" class="form-control-file" id="file">
                            </div>
                            <button type="submit" style="width: 100%;"
                                    class="btn btn-large btn btn-success btn-lg btn-block">
                                <b>Submit</b>
                            </button>
                        </form>
                    </div>
                </div>
                <a class="nav-link" href="./index.jsp"><fmt:message key="page.cancel"/></a>
            </div>
        </div>
    </div>
</div>
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