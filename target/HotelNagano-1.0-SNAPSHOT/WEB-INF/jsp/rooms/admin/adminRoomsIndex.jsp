<%-- 
    Document   : adminRoomsIndex
    Created on : May 22, 2021, 10:53:47 PM
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
            function OpenUpdateRoomModal(button) {
                var room_id = button.value;
                $.ajax({
                   url: 'modify-room',
                   type: 'GET',
                   data: 'roomID=' + room_id,
                   success: function(response) {
                       $('#modalBody').html(response);
                       $('#exampleModal').modal('show');
                   }
                });
            }
            
            function OpenCreateRoomModal() {
                $.ajax({
                   url: 'new-room',
                   type: 'GET',
                   success: function(response) {
                       $('#modalBody').html(response);
                       $('#exampleModal').modal('show');
                   }
                });
            }
            
            function DeleteRoom(button) {
                var confirmation = confirm("Do you want to proceed in deleting the room ?");
                
                if (confirmation) {
                    var room_id = button.value;
                    $.ajax({
                       url: 'delete-room',
                       type: 'POST',
                       data: 'roomID=' + room_id,
                       dataType: 'json',
                       success: function(data) {
                           if (data.roomDeleted) {
                               $('#room_id_' + room_id).remove();
                           }
                       }
                    });
                }
            }
        </script>
        
        <jsp:include page="../../header.jsp"/>
        <jsp:include page="roomsSearchFormPartial.jsp"/>
        
        <button class="container custom_container py-3 mb-3 selection-scale add-room-button" onclick="OpenCreateRoomModal()">
            Add new room
        </button>
        
        <div id="adminRoomList">
            <jsp:include page="adminRoomsListPartial.jsp"/>
        </div>
        
        
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
