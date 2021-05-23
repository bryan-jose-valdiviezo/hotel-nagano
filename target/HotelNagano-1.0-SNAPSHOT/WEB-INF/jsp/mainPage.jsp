<%-- 
    Document   : mainPage
    Created on : May 9, 2021, 1:48:07 AM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <jsp:include page="header.jsp"/>
        
        <div class="container">
            <h2>Welcome to</h2>
            <img src="<c:url value="/resources/data/hotel-nagano-giant-logo.png" />" class="mb-2">
        </div>

            <c:if test="${not empty sessionScope.admin}">
                <jsp:include page="partials/reservations/dailyReservationInfoPartial.jsp"/>
            </c:if>
        
            <c:if test="${empty sessionScope.admin}">
                <jsp:include page="partials/reservations/reservationSearchPartial.jsp" />
            </c:if>
    </body>
</html>
