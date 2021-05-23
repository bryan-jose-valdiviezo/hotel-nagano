<%-- 
    Document   : header
    Created on : May 9, 2021, 4:23:04 AM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<nav class="py-3">
    <a href="/HotelNagano/">Home</a>
    <c:if test="${sessionScope.admin}">
        <a href="/HotelNagano/">Prices</a>
    </c:if>
    <a href="/HotelNagano/rooms/">Rooms</a>
    <div id="logo">
        <img height="50px" src="<c:url value="/resources/data/hotel-nagano-small-logo.png" />">
    </div>
    <a href="">Sign in</a>
    <a id="cartCount" href="/HotelNagano/reservations/new">Cart (${sessionScope.cart.rooms.size()})</a>
    <c:if test="${sessionScope.admin}">
        <a href="/HotelNagano/">Reservations</a>
    </c:if>
</nav>
