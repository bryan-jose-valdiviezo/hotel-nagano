<%-- 
    Document   : reservationSearchPartial
    Created on : May 22, 2021, 2:02:26 PM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<div class="container custom_container">
    <h2>Look for a reservation</h2>
    <form:form id="ReservationSeachForm" modelAttribute="reservationSearchDTO">
        <div class="row">
            <div class="form-group col-lg-6">
                <form:label path="email">Email</form:label>
                <form:input type="text" path="email" class="form-control"/>
            </div>

            <div class="form-group col-lg-6">
                <form:label path="id">Reservation Number</form:label>
                <form:input type="number" path="id" class="form-control"/>
            </div>
        </div>
        <div class="form-group">
            <input type="submit" value="Search" class="submit-button">
        </div>
    </form:form>
</div>