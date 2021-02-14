
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="./views/error.jsp" %>
<html>
<head>
  <meta charset="UTF-8">
  <title>Home page</title>

  <script data-require="jquery@*" data-semver="2.0.3" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
          integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
          crossorigin="anonymous"></script>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">


</head>
<body>
<jsp:include page="./views/header.jsp"/>

<div class="container" style="padding-top: 2rem">
  <div class="row">
    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/xdata/images/hotel/max1024x768/133973579.jpg?k=283f16ac14fac1c458b51ee9d582d26a82414bac06d4fcda72e19bb3d004d3cb&o=" alt="A room assigned to two people. May have one or more beds." data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">Double Room. Room size 20 m² </p>
          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">3 rooms left</small>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/xdata/images/hotel/max1024x768/133969274.jpg?k=d3edb08ca88ef96b19e1e0408ea2299769e2fd483b4581e84614310a1785f7c2&o=" alt="A room assigned to one person. May have one or more beds." data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">Single room. Room size 21 m² </p>

          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">1 room left</small>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/xdata/images/hotel/max1024x768/133973121.jpg?k=274a49d845e167706cccb75fc7690d06d2f1c19fb5956f8642d86519b73a63d6&o=" alt="A room with two beds. May be occupied by one or more people." data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">Twin room. Room size 32 m² </p>
          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">1 room left</small>
          </div>
        </div>
      </div>
    </div>

    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/images/hotel/max1280x900/133/133966735.jpg" alt="A room with a studio bed – a couch that can be converted into a bed. May also have an additional bed" data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">Studio. Room size 22 m²</p>
          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">1 room left</small>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/images/hotel/max1280x900/124/124210304.jpg" alt="A room with a king-sized bed. May be occupied by one or more people." data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">A room with a king-sized bed. 25 m²</p>
          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">2 rooms left</small>
          </div>
        </div>
      </div>
    </div>
    <div class="col-md-4">
      <div class="card mb-4 box-shadow">
        <img class="card-img-top" src="https://cf.bstatic.com/xdata/images/hotel/max1024x768/133973164.jpg?k=973109da752a5b80e9c4775d974950f0c6b5eecef8dbf25c88fbaf0bf74b860b&o=" alt="A room assigned to four people. May have two or more beds." data-holder-rendered="true">
        <div class="card-body">
          <p class="card-text">Quad room. Room size 32 m²</p>
          <div class="d-flex justify-content-between align-items-center">
            <div class="btn-group">
              <a href="./controller?command=bookingdetails&mode=create" role="button" class="btn btn-sm btn-outline-secondary" >Book</a>
            </div>
            <small class="text-muted">1 room left</small>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>

</body>
</html>
