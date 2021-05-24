<%-- 
    Document   : adminReservationsList
    Created on : May 23, 2021, 12:31:28 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:forEach items="${reservations}" var="reservation">
    <a href="/HotelNagano/admin/reservations/${reservation.id}">
        <div class="container custom_container my-2 selection-scale">
            <div class="row py-4">
                <div class="col-sm-3">
                    ${reservation.id}
                </div>

                <div class="col-sm-3">
                    ${reservation.name}
                </div>

                <div class="col-sm-3">
                    ${reservation.email}
                </div>

                <div class="col-sm-3">
                    ${reservation.phone}
                </div>
            </div>
        </div>
    </a>
</c:forEach>