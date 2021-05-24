<%-- 
    Document   : viewPricesListPartial
    Created on : May 22, 2021, 6:34:40 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class='container custom_container pb-1'>
    <h1>Current Views Price</h1>
    
    <c:forEach items="${currentViewPrices}" var="currentView">
        <div class="row mb-4">
            
            <div class="col-sm-6">
                ${currentView.name}:
            </div>
            
            <div class="col-sm-6">
                ${currentView.price} $
            </div>
            
        </div>
    </c:forEach>
    
</div>

<h3>Upcoming/Past Prices</h3>

<c:forEach items="${upcomingViewPrices}" var="viewPrice">
    <div class="row mb-4">
        
        <div class="col-sm-4">
            ${viewPrice.name}
        </div>
        
        <div class="col-sm-4">
            ${viewPrice.price} $
        </div>
        
        <div class="col-sm-4">
            ${viewPrice.dateStart}
        </div>
        
    </div>
</c:forEach>
