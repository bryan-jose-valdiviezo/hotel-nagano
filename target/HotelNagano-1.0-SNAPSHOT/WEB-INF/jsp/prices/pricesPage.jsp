<%-- 
    Document   : pricesPage
    Created on : May 22, 2021, 6:04:01 PM
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
        <script src="<c:url value="/resources/javascript/my_ajax.js" />"></script>
        <script src="<c:url value="/resources/javascript/jquery-3.6.0.min.js" />"></script>
        <script src="<c:url value="/resources/style/bootstrap/js/bootstrap.min.js" />"></script>
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <h1>Prices</h1>
        
        <jsp:include page="ViewPrices/viewPricesContainerPartial.jsp"/>
        
        <jsp:include page="SuitePrices/suitePricesContainerPartial.jsp"/>
        
        <jsp:include page="DayPrices/dayPricesContainerPartial.jsp" />
        
        <jsp:include page="SeasonPrices/seasonPricesContainerPartial.jsp" />
        
        
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div id="modalBody" class="modal-body">
                        ...
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
