<%-- 
    Document   : suitePricesListPartial
    Created on : May 22, 2021, 7:01:31 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class='container custom_container pb-1'>
    <h1>Current Suite Price</h1>
    
    <c:forEach items="${currentSuitePrices}" var="currentSuite">
        <div class="row mb-4">
            
            <div class="col-sm-6">
                ${currentSuite.name}:
            </div>
            
            <div class="col-sm-6">
                ${currentSuite.price} $
            </div>
            
        </div>
    </c:forEach>
    
</div>

<h3>Upcoming/Past Prices</h3>

<c:forEach items="${upcomingSuitePrices}" var="suitePrice">
    <div class="row mb-4">
        
        <div class="col-sm-4">
            ${suitePrice.name}
        </div>
        
        <div class="col-sm-4">
            ${suitePrice.price} $
        </div>
        
        <div class="col-sm-4">
            ${suitePrice.dateStart}
        </div>
        
    </div>
</c:forEach>
