<%-- 
    Document   : seasonPricesFormPartial
    Created on : May 22, 2021, 7:21:53 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    $(document).ready(function() {
        $('#SeasonPriceForm').submit(function (e) {
            e.preventDefault();
            var form = $(this).serialize();
            
            $.ajax({
                url: "create-season-price",
                type: "POST",
                data: form,
                success: function(response) {
                    $("#seasonPricesList").html(response);
                    $("#exampleModal").modal("hide");
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

<form:form id="SeasonPriceForm" modelAttribute="seasonPriceDTO" method="post">
    <div class="row my-3">
        <div class="form-group col-sm-6">
            <form:label path='event'>Event</form:label>
            <form:input path='event' type='text' class='form-control' />
        </div>
            
        <div class="form-group col-sm-6">
            <form:label path="price">Price</form:label>
            <form:input path="price" type="number" step="0.01" class='form-control'/>
        </div>
        
        <div class="form-group col-sm-6">
            <form:label path="dateStart">Date Start</form:label>
            <form:input path="dateStart" type="date" class='form-control'/>
        </div>
        
        <div class="form-group col-sm-6">
            <form:label path="dateEnd">Date End</form:label>
            <form:input path="dateEnd" type="date" class='form-control'/>
        </div>
    </div>
        
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input id="CreateViewPrice" type="submit" value="Add Season Price" class="submit-button">
    </div>
</form:form>