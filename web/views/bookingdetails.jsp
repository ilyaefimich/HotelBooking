<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="vld" uri="/WEB-INF/validation.tld" %>
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
</head>
<body class="bg-light">
<jsp:include page="/views/header.jsp"/>

<c:set var="isReadonly" scope="page" value="readonly"/>
<c:set var="isPaymentReadonly" scope="page" value="readonly"/>
<c:set var="isPaymentDisabled" scope="page" value="disabled"/>
<c:set var="isDisabled" scope="page" value="disabled"/>
<c:set var="isPriceDisabled" scope="page" value="disabled"/>
<c:set var="isRoomDisabled" scope="page" value="disabled"/>
<c:set var="isRoomsHidden" scope="page" value="hidden=\"true\""/>
<c:set var="buttonSubmitText" scope="page" value="Save"/>
<c:set var="newStatus" scope="page" value="1"/>

<c:if test="${mode == \"create\"}">
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
        <c:set var="isRoomsHidden" scope="page" value=""/>
        <c:set var="isPriceDisabled" scope="page" value=""/>
        <c:set var="isRoomDisabled" scope="page" value=""/>
        <c:set var="isReadonly" scope="page" value="readonly"/>
        <c:set var="isDisabled" scope="page" value="disabled"/>
        <c:set var="buttonSubmitText" scope="page" value="Propose room"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 2}">
    <c:set var="newStatus" scope="page" value="3"/>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
        <c:set var="isRoomsHidden" scope="page" value=""/>
        <c:set var="isPaymentReadonly" scope="page" value=""/>
        <c:set var="isPaymentDisabled" scope="page" value=""/>
        <c:set var="buttonSubmitText" scope="page" value="Confirm"/>
    </c:if>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="buttonSubmitText" scope="page" value="Save"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 3}">
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
        <c:set var="buttonSubmitText" scope="page" value="Confirm booking 1"/>
        <c:set var="isPaymentReadonly" scope="page" value="readonly"/>
        <c:set var="isPaymentDisabled" scope="page" value="disabled"/>
    </c:if>
    <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
        <c:set var="isPaymentReadonly" scope="page" value="readonly"/>
        <c:set var="isPaymentDisabled" scope="page" value="disabled"/>
    </c:if>
</c:if>
<c:if test="${booking.getBookingStatus().bookingStatusId == 5}">
    <c:set var="isRoomsHidden" scope="page" value=""/>
</c:if>

<c:if test="${mode == \"update\" }">
<form class="form-inline needs-validation" method="post" action="./controller?command=updatebooking&mode=update">
    </c:if>

    <c:if test="${mode == \"create\" }">
    <form class="form-inline needs-validation" method="post" action="./controller?command=updatebooking&mode=create">
        </c:if>

        <input type="number" class="custom-control-input" name="userid" id="userid" value="${loggeduser.userId}"
               hidden="true">
        <div class="col-md-8 order-md-1"
             style="padding-left: 30rem; padding-right: 10rem; padding-top: 10rem; padding-bottom: 10rem">
            <vld:error errorMessage="${error}"/>
            <div class="block">
                <h4 class="mb-3"><fmt:message key="page.booking_info"/></h4>
                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="checkin"><fmt:message key="page.booking_checkin"/></label>


                        <input name="checkin" type="date" class="form-control" id="checkin" placeholder="01.01.2021"
                               required="required"
                        <c:out value="${isCheckinInvalid}"/>
                               value="${booking.checkInDate}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            <c:out value="${checkinValidationMessage}"/>
                        </div>

                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="checkout"><fmt:message key="page.booking_checkout"/></label>
                        <input name="checkout" type="date" class="form-control" id="checkout" placeholder="10.01.2021"
                               required="required"
                               value="${booking.checkOutDate}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            <fmt:message key="page.dateNameFeedback"/>
                        </div>
                    </div>
                </div>
                <div class="row">
                    <input name="bookingid" type="number" class="form-control" id="bookingid"
                           value="${booking.bookingId}"
                           hidden="true">

                    <div class="col-md-6 mb-3">
                        <label for="adults"><fmt:message key="page.booking_adults"/></label>
                        <input name="adults" type="number" class="form-control" id="adults" placeholder=""
                               required="required"
                               value="${booking.adultsCount}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            <fmt:message key="page.numberFeedback"/>
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="children"><fmt:message key="page.booking_children"/></label>
                        <input type="number" class="form-control" name="children" id="children" placeholder=""
                               required="required"
                               value="${booking.childrenCount}" <c:out value="${isReadonly}"/>>
                        <div class="invalid-feedback">
                            <fmt:message key="page.numberFeedback"/>
                        </div>
                    </div>
                    <div class="col-md-5 mb-3">
                        <label for="roomtype"><fmt:message key="page.booking_room_type"/></label>
                        <select class="form-select" name="roomtype" id="roomtype" required="required"
                                <c:out value="${isDisabled}"/>>
                            <option value="1"
                                    <c:if test="${booking.getRoomType().roomTypeId == 1}">
                                        selected="selected"
                                    </c:if>
                            >Single
                            </option>
                            <option value="2"
                                    <c:if test="${booking.getRoomType().roomTypeId == 2}">
                                        selected="selected"
                                    </c:if>
                            >Double
                            </option>
                            <option value="3"
                                    <c:if test="${booking.getRoomType().roomTypeId == 3}">
                                        selected="selected"
                                    </c:if>
                            >Triple
                            </option>
                            <option value="4"
                                    <c:if test="${booking.getRoomType().roomTypeId == 4}">
                                        selected="selected"
                                    </c:if>
                            >Quad
                            </option>
                            <option value="5"
                                    <c:if test="${booking.getRoomType().roomTypeId == 5}">
                                        selected="selected"
                                    </c:if>
                            >Queen
                            </option>
                            <option value="6"
                                    <c:if test="${booking.getRoomType().roomTypeId == 6}">
                                        selected="selected"
                                    </c:if>
                            >King
                            </option>
                            <option value="7"
                                    <c:if test="${booking.getRoomType().roomTypeId == 7}">
                                        selected="selected"
                                    </c:if>
                            >Twin
                            </option>
                            <option value="8"
                                    <c:if test="${booking.getRoomType().roomTypeId == 8}">
                                        selected="selected"
                                    </c:if>
                            >Studio
                            </option>
                        </select>
                        <div class="invalid-feedback">
                            Please select a valid room type.
                        </div>
                    </div>
                    <div class="col-md-5 mb-3" <c:out value="${isRoomsHidden}"/> >
                        <label for="roomid"><fmt:message key="page.booking_room"/></label>
                        <select class="form-select " name="roomid" id="roomid" onchange="pricefill()" <c:out value="${isRoomDisabled}"/>>
                            <c:forEach var="room" items="${rooms}" varStatus="index">
                                <option value="<c:out value="${room.roomId}"/>"
                                        <c:if test="${booking.getOfferedRoom().roomId == room.roomId}">
                                            selected="selected"
                                        </c:if>
                                ><c:out value="${room.name}"/></option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a room.
                        </div>
                        <select id="roomselection" hidden="true">
                            <c:forEach var="room" items="${rooms}">
                                <option value="${room.roomId}"><c:out value="${room.price}"/></option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="col-md-5 mb-3">
                        <label for="rateType"><fmt:message key="page.booking_ratetypes"/></label>
                        <select class="form-select" name="rateType" id="rateType" required="required"
                                <c:out value="${isDisabled}"/>>
                            <c:forEach var="rateType" items="${rateTypes}" varStatus="index">
                                <option value="<c:out value="${rateType.rateTypeId}"/>"
                                        <c:if test="${booking.getRateType().rateTypeId == rateType.rateTypeId}">
                                            selected="selected"
                                        </c:if>
                                ><c:out value="${rateType.rateName}"/></option>
                            </c:forEach>
                        </select>
                        <div class="invalid-feedback">
                            Please select a rate type.
                        </div>
                    </div>
                    <div class="col-md-5 mb-3" <c:out value="${isRoomsHidden}"/>>
                        <label for="price"><fmt:message key="page.booking_price"/></label>
                        <input type="text" class="form-control" name="price" id="price" placeholder=""
                               value="${booking.price}" <c:out value="${isPriceDisabled}"/>>
                    </div>

                    <div class="col-md-6 mb-3">
                        <label for="comment"><fmt:message key="page.booking_comment"/></label>
                        <input type="text" class="form-control" name="comment" id="comment" placeholder=""
                               value="${booking.comment}" <c:out value="${isReadonly}"/>>
                    </div>
                </div>

                <h4 class="mb-3"><fmt:message key="page.guestInfo"/></h4>

                <div class="col-md-6 mb-3">
                    <label for="guestName"><fmt:message key="page.guestFullName"/></label>
                    <input type="text" class="form-control" id="guestName" placeholder="John Smith" required="required"
                           value="${guest.name}" readonly>
                    <div class="invalid-feedback">
                        Valid guest name is required.
                    </div>
                </div>
                <div class="col-md-6 mb-3">
                    <label for="guestMobile"><fmt:message key="page.guestMobile"/></label>
                    <input type="text" class="form-control" id="guestMobile" placeholder="+71028493028"
                           required="required"
                           value="${guest.mobile}" readonly>
                    <div class="invalid-feedback">
                        Valid guest's mobile phone number is required.
                    </div>
                </div>
                <div class="mb-3">
                    <label for="email"><fmt:message key="page.emailAddress"/><span class="text-muted">(Optional)</span></label>
                    <input type="email" class="form-control" id="email" placeholder="you@example.com"
                           value="${guest.email}" readonly>
                    <div class="invalid-feedback">
                        Please enter a valid email address.
                    </div>
                </div>

                <div class="mb-3">
                    <label for="address"><fmt:message key="page.address"/></label>
                    <input type="text" class="form-control" id="address" placeholder="1234 Main St"
                           value="${guest.address}" readonly>
                    <div class="invalid-feedback">
                        Please enter your shipping address.
                    </div>
                </div>
            </div>
            <c:if test="${mode == \"create\"}">
                <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="1" hidden="true">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><c:out
                            value="${buttonSubmitText}"/></button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.createBooking"/></button>
                </c:if>
            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId >= 2}">
                <h4 class="mb-3"><fmt:message key="page.payment"/></h4>
                <div class="d-block my-3">
                    <div class="custom-control custom-radio" >
                        <input id="credit" name="paymentMethod" type="radio" class="custom-control-input"
                               checked="checked" <c:out value="${isPaymentDisabled}"/>>
                        <label class="custom-control-label" for="credit"><fmt:message key="page.creditCard"/></label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="debit" name="paymentMethod" type="radio" class="custom-control-input"
                            <c:out value="${isPaymentDisabled}"/>>
                        <label class="custom-control-label" for="debit"><fmt:message key="page.debitCard"/></label>
                    </div>
                    <div class="custom-control custom-radio">
                        <input id="paypal" name="paymentMethod" type="radio" class="custom-control-input"
                            <c:out value="${isPaymentDisabled}"/>>
                        <label class="custom-control-label" for="paypal"><fmt:message key="page.paypal"/></label>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-6 mb-3">
                        <label for="cardName"><fmt:message key="page.nameOnCard"/></label>
                        <input type="text" class="form-control" id="cardName" name="cardName" placeholder=""
                            <c:out value="${isPaymentReadonly}"/>
                               value="${booking.getPreferedPaymentMethod().cardholderName}">
                        <small class="text-muted"><fmt:message key="page.cardHolderName"/></small>
                        <div class="invalid-feedback">
                            Name on card is required
                        </div>
                    </div>
                    <div class="col-md-6 mb-3">
                        <label for="cardNumber"><fmt:message key="page.creditCardNumber"/></label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" placeholder=""
                            <c:out value="${isPaymentReadonly}"/>
                               value="${booking.getPreferedPaymentMethod().cardNumber}">
                        <div class="invalid-feedback">
                            Credit card number is required
                        </div>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-3 mb-3">
                        <label for="expirationDate"><fmt:message key="page.expiration"/></label>
                        <input type="text" class="form-control" id="expirationDate" name="expirationDate" placeholder=""
                            <c:out value="${isPaymentReadonly}"/>
                               value="${booking.getPreferedPaymentMethod().expirationDate}">
                        <div class="invalid-feedback">
                            Expiration date required
                        </div>
                    </div>
                    <div class="col-md-3 mb-3">
                        <label for="cvvCode"><fmt:message key="page.CVV"/></label>
                        <input type="text" class="form-control" id="cvvCode" name="cvvCode" placeholder=""
                            <c:out value="${isPaymentReadonly}"/> value="${booking.getPreferedPaymentMethod().csvCode}">
                        <div class="invalid-feedback">
                            Security code required
                        </div>
                    </div>
                </div>
                <hr class="mb-4">

            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId == 2 || booking.getBookingStatus().bookingStatusId == 3}">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <div class="custom-control custom-checkbox" >
                        <input type="checkbox" class="custom-control-input" id="save-info" <c:out value="${isPaymentDisabled}"/>>
                        <label class="custom-control-label" for="save-info"><fmt:message key="page.saveInfo"/></label>
                    </div>
                    <hr class="mb-4">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="5"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.confirm"/></button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="5"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.confirmBooking"/></button>
                </c:if>
            </c:if>
            <c:if test="${booking.getBookingStatus().bookingStatusId == 1}">
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"guest\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="4"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.cancelBooking"/></button>
                </c:if>
                <c:if test="${loggeduser.getUserRole().getRoleName().toLowerCase().equals(\"admin\") }">
                    <input type="text" class="custom-control-input" name="newStatus" id="newStatus" value="2"
                           hidden="true">
                    <button class="btn btn-primary btn-lg btn-block" type="submit"><fmt:message key="page.proposeRoom"/></button>
                </c:if>
            </c:if>
            <a href="./controller?command=getbookings" id="cancel" name="cancel"
               class="btn btn-primary btn-lg btn-block"><fmt:message key="page.cancel"/></a>
        </div>
    </form>

    <script>
        /*
                $(function () {
                    var local = new Date(this);
                    document.getElementById('checkin').valueAsDate = local;
                    document.getElementById('checkout').valueAsDate = new Date(local.getFullYear(), local.getMonth(), local.getDay()+1);
                };
        */

        function pricefill() {
            var select = document.getElementById("roomid");
            var selectManager = document.getElementById("roomselection");
            var x = select.selectedIndex;
            document.getElementById("price").value = selectManager.options[x].text;
        }

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
