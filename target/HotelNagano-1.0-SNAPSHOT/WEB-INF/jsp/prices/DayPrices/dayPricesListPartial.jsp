<%-- 
    Document   : dayPricesListPartial
    Created on : May 22, 2021, 7:07:11 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class='container custom_container pb-1'>
    <h1>Current Day Prices</h1>
    
    <c:forEach items="${currentDayPrices}" var="currentDay">
        <div class="row mb-4">
            
            <div class="col-sm-6">
                ${currentDay.dayType}:
            </div>
            
            <div class="col-sm-6">
                ${currentDay.price} $
            </div>
            
        </div>
    </c:forEach>
    
</div>

<h3>Upcoming/Past Prices</h3>

<c:forEach items="${upcomingDayPrices}" var="dayPrice">
    <div class="row mb-4">
        
        <div class="col-sm-4">
            ${dayPrice.dayType}
        </div>
        
        <div class="col-sm-4">
            ${dayPrice.price} $
        </div>
        
        <div class="col-sm-4">
            ${dayPrice.dateStart}
        </div>
        
    </div>
</c:forEach>