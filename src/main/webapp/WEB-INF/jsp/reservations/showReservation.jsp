<%-- 
    Document   : showReservation
    Created on : May 12, 2021, 3:52:13 AM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
            function OpenUpdateReservationModal() {
                var url = window.location.href;  
                
                $.ajax({
                   url: url+'/modify-reservation',
                   type: 'GET',
                   success: function(response) {
                       $('#modalBody').html(response);
                       $('#exampleModal').modal('show');
                   }
                });
            }
            
            function DeleteReservation() {
                var confirmation = confirm("Do you want to proceed in deleting the reservation ?");
                
                if (confirmation) {
                   var url = window.location.href;
                    $.ajax({
                       url: url+'/delete-reservation',
                       type: 'POST',
                       success: function(response) {
                           window.location = "/HotelNagano/admin/reservations/"
                       }
                    }); 
                }
            }
        </script>
        
        <jsp:include page="../header.jsp" />
        
        <div id='reservationSumary'>
            <jsp:include page="reservationInfoPartial.jsp" />
        </div>
               
        <c:if test="${not empty sessionScope.user && sessionScope.user.role == 'ADMIN'}">
            <div class="container custom_container py-4 mb-3">
                <div class="row">
                    <div class="col-sm-6">
                        <button type='button' class="submit-button" onclick="OpenUpdateReservationModal()">
                            Edit Reservation
                        </button>
                    </div>
                    <div class="col-sm-6">
                        <button type='button' class="submit-button" onclick='DeleteReservation()'>
                            Delete Reservation
                        </button>
                    </div>
                </div>
            </div>
        </c:if>
                                        
        <div class="modal fade bd-example-modal-lg" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered modal-lg" role="document">
                <div class="modal-content">
                    <div id="modalBody" class="modal-body" style='max-height: 800px;'>
                        ...
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
