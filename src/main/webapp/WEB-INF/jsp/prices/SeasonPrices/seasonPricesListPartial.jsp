<%-- 
    Document   : seasonPricesListPartial
    Created on : May 22, 2021, 7:06:53 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class='container custom_container pb-1'>
    <h1>Current Season Price</h1>
    
    <div class="row mb-4">
        
        <c:if test="${empty currentSeasonPrice}">
            <div class="col-sm-12">
                None
            </div>
        </c:if>
        
        <c:if test="${not empty currentSeasonPrice}">
            <div class="col-sm-3">
                ${currentSeasonPrice.event}
            </div>
            
            <div class="col-sm-3">
                ${currentSeasonPrice.price} $
            </div>

            <div class="col-sm-3">
                ${currentSeasonPrice.dateStart}
            </div>
            
            <div class="col-sm-3">
                ${currentSeasonPrice.dateEnd}
            </div>
        </c:if>

    </div>
    
</div>

<h3>Upcoming/Past Prices</h3>

<c:forEach items="${upcomingSeasonPrices}" var="season">
    <div class="row mb-4">
        
        <div class="col-sm-3">
            ${season.event}
        </div>
        
        <div class="col-sm-3">
            ${season.price} $
        </div>
        
        <div class="col-sm-3">
            ${season.dateStart}
        </div>
        
        <div class="col-sm-3">
            ${season.dateEnd}
        </div>
        
    </div>
</c:forEach>