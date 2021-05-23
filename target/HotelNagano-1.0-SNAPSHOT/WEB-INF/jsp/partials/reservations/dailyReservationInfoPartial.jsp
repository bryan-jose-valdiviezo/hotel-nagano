<%-- 
    Document   : dailyReservationInfoPartial.jsp
    Created on : May 22, 2021, 2:12:54 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="container custom_container py-4 mb-3">
    <h2>Rooms Availability</h2>
    <div class="row">
        <div class="col-sm-6">
            ${todayAvailableRooms}/${totalRooms}
        </div>
        <div class="col-sm-6 pb-3">
            ${percentCapacity}% Capacity
        </div>
    </div>
</div>

<div class="container custom_container py-4">
    <h2>Today's Income</h2>
    <div class="row">
        <div class="col-sm-12 pb-3">
            ${todayIncome} $
        </div>
    </div>
</div>
