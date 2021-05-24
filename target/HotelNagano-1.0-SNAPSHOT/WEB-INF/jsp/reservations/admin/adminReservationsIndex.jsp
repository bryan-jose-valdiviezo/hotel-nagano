<%-- 
    Document   : adminReservationsIndex
    Created on : May 23, 2021, 12:25:26 PM
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
        <script>
            $(document).ready(function() {
               $('#ReservationSeachForm').submit(function(e) {
                  e.preventDefault();
                  var form = $(this).serialize();
                  
                  $('#reservationListContent').fadeOut('fast', function() {
                      $.ajax({
                        url: "search",
                        type: 'GET',
                        data: form,
                        success: function(response) {
                            $('#reservationListContent').html(response);
                            $('#reservationListContent').fadeIn(500);
                        }
                     });
                  });
               });
            });
        </script>
        
        <jsp:include page="../../header.jsp"/>
        
        <div class="container my-3">
            <img src="<c:url value="/resources/data/hotel-nagano-giant-logo.png" />" class="mb-2">
        </div>
        
        <jsp:include page="../../partials/reservations/reservationSearchPartial.jsp" />
        
        <div id="reservationListContent">
            <jsp:include page="adminReservationsList.jsp" />
        </div>
    </body>
</html>
