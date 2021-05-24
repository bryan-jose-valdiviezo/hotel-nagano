<%-- 
    Document   : adminReservationForm
    Created on : May 23, 2021, 2:05:26 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    $(document).ready(function() {
        $('#adminReservationForm').submit(function(e) {
            e.preventDefault();
            var form = $(this).serialize();
            
            $.ajax({
                url: "update-reservation",
                type: "POST",
                data: form,
                success: function(response) {
                    $("#reservationSumary").html(response);
                    $("#exampleModal").modal("hide");
                }
            });
        });
        
        $(':checkbox').change(function() {
            var ids = [];
            var dateStart = $('#dateStartInput').val();
            var dateEnd = $('#dateEndInput').val();
            
           $("input:checkbox[name=roomsID]:checked").each(function(){
                ids.push($(this).val());
            });
            
            
            $.ajax({
                url: "change-reservation-price",
                type: "POST",
                dataType: 'json',
                data: { dateStart: dateStart, dateEnd: dateEnd, ids: ids },
                success: function(data) {
                    $('#price').attr('value', data.newPrice);
                }
            });
        });
    });
</script>

<div class="modal-header">
    <h5 class="modal-title" id="exampleModalLabel">Modal title</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>

<form:form id="adminReservationForm" modelAttribute="adminReservationDTO">
    <div class="row">
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='id'>ID</form:label>
            <form:input type='number' path='id' readonly="true" class='form-control w-75' />
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='name'>Name</form:label>
            <form:input type='text' path='name' class='form-control w-75' />
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='dateStart'>Date Start</form:label>
            <form:input id="dateStartInput" type='date' path='dateStart' readonly="true" class='form-control w-75' />
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='dateEnd'>Date End</form:label>
            <form:input id="dateEndInput" type='date' path='dateEnd' readonly="true" class='form-control w-75' />
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='customerCount'>Customer Count</form:label>
            <form:input type='number' path='customerCount' class='form-control w-75' />
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path="price">Total Price</form:label>
            <form:input id="price" path="price" type="number" step="0.01" class='form-control w-75'/>
        </div>
        
        <div class="form-group col-sm-12" align="center">
            <form:label path="specialInstructions">Special instructions</form:label>
            <form:textarea path="specialInstructions" class='form-control w-50 '/>
        </div>
        
        <c:forEach items="${rooms}" var="room">
            <div class="form-group col-sm-12">
                <form:checkbox path="roomsID" value="${room.id}"/> ${room.suite.name} / ${room.view.name} / ${room.floor}
            </div>
        </c:forEach>
        
    </div>
            
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input id="UpdateReservationButton" type="submit" value="Update Reservation" class="submit-button">
    </div>
</form:form>
