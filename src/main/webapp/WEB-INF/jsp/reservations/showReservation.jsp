<%-- 
    Document   : showReservation
    Created on : May 12, 2021, 3:52:13 AM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/style/bootstrap/css/bootstrap.min.css?version=51" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/style/style.css?version=51" />" rel='stylesheet' type="text/css" />
        <script src="../resources/javascript/my_ajax.js"></script>
        <script src="../resources/javascript/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp" />
        <h1 class="mt-3">Reservation for ${reservation.name}</h1>
        <div class="container-fluid p-5 d-block">
            <div class="row">
                <div id="reservation_customer_information" class="col-sm-12 mb-5">
                    <table class="float-right">
                        <tr>
                            <th>Reservation #</th>
                            <td>${reservation.id}</td>
                        </tr>

                        <tr>
                            <th>Name</th>
                            <td>${reservation.name}</td>
                        </tr>

                        <tr>
                            <th>E-mail</th>
                            <td>${reservation.email}</td>
                        </tr>

                        <tr>
                            <th>Reservation Start</th>
                            <td>${reservation.dateStart.toString()}</td>
                        </tr>

                        <tr>
                            <th>Reservation End</th>
                            <td>${reservation.dateEnd.toString()}</td>
                        </tr>

                        <tr>
                            <th>Amount due</th>
                            <td>${reservation.totalPrice()}</td>
                        </tr>

                    </table>
                </div>
                <div id="reservation_rooms_information" class="col-sm-12">
                    <table class="w-100">
                        <tr>
                            <th>Room</th>
                            <th>Description</th>
                            <th>Price</th>
                        </tr>
                        <c:forEach items="${reservation.rooms}" var="room">
                            <tr>
                                <td>${room.suite.name}</td>
                                <td>
                                    View: ${room.suite.name}<br>
                                    Floor: ${room.floor}
                                </td>
                                <td>${room.roomPrice()} $</td>
                            </tr>
                        </c:forEach>
                        <tr style="height:50px;">
                            <td colspan="3"></td>
                        </tr>
                        <tr>
                            <td></td>
                            <th>Total: </th>
                            <th>${reservation.totalPrice()} $</th>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
