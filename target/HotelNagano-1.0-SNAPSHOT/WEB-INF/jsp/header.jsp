<%-- 
    Document   : header
    Created on : May 9, 2021, 4:23:04 AM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<nav class="py-3">
    <a href="/HotelNagano/">Home</a>
    <a href="/HotelNagano/rooms/">Rooms</a>
    <div id="logo">
        <img height="50px" src="<c:url value="/resources/data/hotel-nagano-small-logo.png" />">
    </div>
    <%
        if(session.getAttribute("user") == null)
        {
    %>
    <a href="/HotelNagano/login/loginPage/">Sign in</a>
    <%
        }
    %>
    <%
        if(session.getAttribute("user") != null)
        {
    %>
    <a href="/HotelNagano/login/logoutPage">Sign out</a>
    <%
        }
    %>
    <a id="cartCount" href="/HotelNagano/reservations/new">Cart (${sessionScope.cart.rooms.size()})</a>
</nav>


