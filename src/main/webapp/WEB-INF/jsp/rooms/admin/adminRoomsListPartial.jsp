<%-- 
    Document   : adminRoomsListPartial
    Created on : May 22, 2021, 11:19:38 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:forEach items="${rooms}" var="room">
    <%@include file="adminRoomsDivPartial.jsp" %>
    
</c:forEach>