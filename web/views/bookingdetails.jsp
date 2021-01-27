<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${locale}" scope="session"/>
<fmt:setBundle basename="properties.content"/>
<%@ page isELIgnored="false" language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" errorPage="error.jsp" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Booking details</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha2/css/bootstrap.min.css"
          integrity="sha384-DhY6onE6f3zzKbjUPRc2hOzGAdEf4/Dz+WJwBvEYL/lkkIsI3ihufq9hk9K4lVoK" crossorigin="anonymous">

    <!-- Custom styles for this template -->
    <link href="%D0%9F%D0%BE%D0%BB%D1%8C%D0%B7%D0%BE%D0%B2%D0%B0%D1%82%D0%B5%D0%BB%D1%8C%D1%81%D0%BA%D0%B0%D1%8F%20%D1%84%D0%BE%D1%80%D0%BC%D0%B0%20Checkout%20example%20for%20Bootstrap%20(BS%204.0)_files/form-validation.css"
          rel="stylesheet">

</head>
<body class="bg-light">
<jsp:include page="/views/header.jsp"/>

<c:set var="isReadonly" scope="page" value="readonly"/>
<c:set var="isDisabled" scope="page" value="disabled"/>
<c:set var="isRoomsDisabled" scope="page" value="disabled"/>
<c:set var="buttonSubmitText" scope="page" value="Save"/>
<c:set var="newStatus" scope="page" value="1"/>

<c:if test="${booking == null}">
    <c:set var="newStatus" scope="page" value="1"/>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
        <c:set var="isReadonly" scope="page" value=""/>
        <c:set var="isDisabled" scope="page" value=""/>
        <c:set var="buttonSubmitText" scope="page" value="Request booking"/>
    </c:if>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="isReadonly" scope="page" value=""/>
        <c:set var="isDisabled" scope="page" value=""/>
        <c:set var="buttonSubmitText" scope="page" value="Create booking"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 1}">
    <c:set var="newStatus" scope="page" value="2"/>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
        <c:set var="buttonSubmitText" scope="page" value="Cancel booking"/>
    </c:if>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="isRoomsDisabled" scope="page" value=""/>
        <c:set var="isReadonly" scope="page" value=""/>
        <c:set var="isDisabled" scope="page" value=""/>
        <c:set var="buttonSubmitText" scope="page" value="Propose room"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 2}">
    <c:set var="newStatus" scope="page" value="3"/>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
        <c:set var="buttonSubmitText" scope="page" value="Confirm"/>
    </c:if>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="isReadonly" scope="page" value=""/>
        <c:set var="isDisabled" scope="page" value=""/>
        <c:set var="buttonSubmitText" scope="page" value="Save"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 3}">
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="buttonSubmitText" scope="page" value="Confirm booking"/>
    </c:if>
</c:if>

<c:if test="${mode == \"update\" }">
<form class="form-inline needs-validation" method="post" action="./controller?command=updatebooking&mode=update">
    </c:if>

    <c:if test="${mode == \"create\" }">
    <form class="form-inline needs-validation" method="post" action="./controller?command=updatebooking&mode=create">
        </c:if>

        <input type="number" class="custom-control-input" name="guestid" id="guestid" value="${loggeduser.userId}" hidden="true">
        <div class="col-md-8 order-md-1"
             style="padding-left: 30rem; padding-right: 10rem; padding-top: 10rem; padding-bottom: 10rem">
            <div class="block">
                <h4 class="mb-3">Booking Information</h4>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="checkin">Check In</label>
                        <c:if test="${error == 123}">
                            <c:set var="isCheckinInvalid" scope="page" value="is-invalid"/>
                            <c:set var="checkinValidationMessage" scope="page"
                                   value="Check-in Date Has To Be Befor Checkout date"/>
                        </c:if>

                        <input name="checkin" type="date" class="form-control" id="checkin" placeholder="01.01.2021"
                               required="required"
                        <c:out value="${isCheckinInvalid}"/>
                               value="${booking.checkInDate}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            <c:out value="${checkinValidationMessage}"/>
                        </div>

                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="checkout">Check Out</label>
                        <input name="checkout" type="date" class="form-control" id="checkout" placeholder="10.01.2021"
                               required="required"
                               value="${booking.checkOutDate}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            Valid date name is required.
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input name="bookingid" type="number" class="form-control" id="bookingid"
                           value="${booking.bookingId}"
                           hidden="true">

                    <div class="col-md-6 mb-3">
                        <label for="adults">Adults</label>
                        <input name="adults" type="number" class="form-control" id="adults" placeholder=""
                               required="required"
                               value="${booking.adultsCount}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            Valid number is required.<c:out value="${isReadonly}"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="children">Children</label>
                        <input type="number" class="form-control" name="children" id="children" placeholder="" required="required"
                               value="${booking.childrenCount}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            Valid number is required.
                        </div>
                    </div>
                    <div class="col-md-5 mb-3">
                        <select class="form-select" name="roomtype" id="roomtype" required="required" <c:out value="${isDisabled}"/>>
                            <option value="1"
                                    <c:if test="${booking.getRoomType().roomTypeId == 1}">
                                        selected="selected"
                                    </c:if>
                            >Single</option>
                            <option value="2"
                                    <c:if test="${booking.getRoomType().roomTypeId == 2}">
                                        selected="selected"
                                    </c:if>
                            >Double</option>
                            <option value="3"
                                    <c:if test="${booking.getRoomType().roomTypeId == 3}">
                                        selected="selected"
                                    </c:if>
                            >Triple</option>
                            <option value="4"
                                    <c:if test="${booking.getRoomType().roomTypeId == 4}">
                                        selected="selected"
                                    </c:if>
                            >Quad</option>
                            <option value="5"
                                    <c:if test="${booking.getRoomType().roomTypeId == 5}">
                                        selected="selected"
                                    </c:if>
                            >Queen</option>
                            <option value="6"
                                    <c:if test="${booking.getRoomType().roomTypeId == 6}">
                                        selected="selected"
                                    </c:if>
                            >King</option>
                            <option value="7"
                                    <c:if test="${booking.getRoomType().roomTypeId == 7}">
                                        selected="selected"
                                    </c:if>
                            >Twin</option>
                            <option value="8"
                                    <c:if test="${booking.getRoomType().roomTypeId == 8}">
                                        selected="selected"
                                    </c:if>
                            >Studio</option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid room type.
                        </div>
                    </div>
                    <div class="col-md-5 mb-3">
                        <select class="form-select" name="room" id="room" required="required" <c:out value="${isDisabled}"/> <c:out value="${isRoomsDisabled}"/>>
                            <label for="room">Avaiable Rooms</label>
                            <c:forEach var="room" items="${rooms}" varStatus="index">
                                <option id="<c:out value="${room.roomId}"/>"><c:out value="${room.name}"/></option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid room type.
                        </div>
                    </div>
                </div>

                <h4 class="mb-3">Guest Information</h4>

                <div class="col-md-6 mb-3">
                    <label for="guestName">Full name</label>
                    <input type="text" class="form-control" id="guestName" placeholder="John Smith" required="required"
                           value="${booking.getGuest().name}" <c:out value="${isReadonly}"/>>
                    <div class="invalid-feedback">
                        Valid guest name is required.
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="guestMobile">Mobile</label>
                    <input type="text" class="form-control" id="guestMobile" placeholder="+71028493028"
                           required="required"
                           value="${booking.getGuest().mobile}" <c:out value="${isReadonly}"/>>
                    <div class="invalid-feedback">
                        Valid guest's mobile phone number is required.
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email">Email <span class="text-muted">(Optional)</span></label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com"
                           value="${booking.getGuest().email}" <c:out value="${isReadonly}"/>>
                    <div class="invalid-feedback">
                        Please enter a valid email address.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="address">Address</label>
                    <input type="text" class="form-control" id="address" placeholder="1234 Main St"
                           value="${booking.getGuest().address}" <c:out value="${isReadonly}"/>>
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>
                <div class="col-md-5 mb-3">
                    <label for="city">City</label>
                    <select class="form-select" id="city" <c:out value="${isDisabled}"/>>
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
                        <select class="form-select" id="state" <c:out value="${isDisabled}"/>>
                            <option value="" selected="selected">Choose...</option>
                            <option>Minsk</option>
                        </select>
                        <div class="invalid-feedback">
                            Please provide a valid state.
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="zip">Zip</label>
                        <input type="text" class="form-select" id="zip" placeholder="" <c:out value="${isDisabled}"/>>
                        <div class="invalid-feedback">
                            Zip code required.
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${booking == null}">
                <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="1" hidden="true">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><c:out
                            value="${buttonSubmitText}"/></button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Create booking</button>
                </c:if>
            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId == 1}">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="4"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Cancel booking</button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="2"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Propose room</button>
                </c:if>
            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId == 2}">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="3"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Confirm</button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="2"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Save</button>
                </c:if>
            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId == 3}">

                <h4 class="mb-3">Payment</h4>
                <div class="d-block my-3">
                    <div class="custom-control custom-radio">
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input"
                               checked="checked">
                        <label class="custom-control-label" for="credit">Credit card</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input">
                        <label class="custom-control-label" for="debit">Debit card</label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input">
                        <label class="custom-control-label" for="paypal">Paypal</label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cc-name">Name on card</label>
                        <input type="text" class="form-control" id="cc-name" placeholder="">
                        <small class="text-muted">Full name as displayed on card</small>
                        <div class="invalid-feedback">
                            Name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cc-number">Credit card number</label>
                        <input type="text" class="form-control" id="cc-number" placeholder="">
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">Expiration</label>
                        <input type="text" class="form-control" id="cc-expiration" placeholder="">
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cc-expiration">CVV</label>
                        <input type="text" class="form-control" id="cc-cvv" placeholder="">
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>

                <hr class="mb-4">
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="same-address">
                    <label class="custom-control-label" for="same-address">Shipping address is the same as my billing
                        address</label>
                </div>
                <div class="custom-control custom-checkbox">
                    <input type="checkbox" class="custom-control-input" id="save-info">
                    <label class="custom-control-label" for="save-info">Save this information for next time</label>
                </div>
                <hr class="mb-4">

                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="5"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit">Confirm booking</button>
                </c:if>
            </c:if>
            <a href="./controller?command=bookings" id="cancel" name="cancel" class="btn btn-primary btn-lg btn-block">Cancel</a>
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

        $('#roomtype').change(function(event) {
            var $location=$("select#roomtype").val();
            $.get('SvrController',{location:$location},function(responseJson) {
                var $select = $('#svrAddr');
                $select.find('option').remove();
                $.each(responseJson, function(index, name) {
                    $('<option>').val(index).text(name).appendTo($select);
                });
            },'json');
        });
    </script>

</body>
</html>
