<%-- 
    Document   : adminRoomsDivPartial
    Created on : May 23, 2021, 10:59:09 AM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="room_id_${room.id}">
    <button id="admin_room_id_${room.id}" class="container custom-top-half-container py-4 mb-0" value="${room.id}" onclick="OpenUpdateRoomModal(this)">
        <%@include file="adminRoomsRowPartial.jsp" %>
    </button>

    <button class="container custom-bottom-half-container mb-3 py-2" value="${room.id}" onclick="DeleteRoom(this)">
        Delete
    </button>
</div>