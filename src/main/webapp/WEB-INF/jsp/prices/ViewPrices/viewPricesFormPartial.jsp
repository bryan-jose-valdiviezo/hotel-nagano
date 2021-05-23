<%-- 
    Document   : viewPricesFormPartial
    Created on : May 22, 2021, 7:22:17 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    $(document).ready(function() {
        $('#ViewPriceForm').submit(function (e) {
            e.preventDefault();
            var form = $(this).serialize();
            
            $.ajax({
                url: "create-view-price",
                type: "POST",
                data: form,
                success: function(response) {
                    $("#viewPricesList").html(response);
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

<form:form id="ViewPriceForm" modelAttribute="viewPriceDTO" method="post">
    <div class="row my-3">
        <div class="form-group col-sm-6">
            <form:label path='viewID'>View</form:label>
            <form:select path='viewID' class="form-control">
                <c:forEach items="${views}" var="view">
                    <form:option value="${view.id}">${view.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
            
        <div class="form-group col-sm-6">
            <form:label path="price">Price</form:label>
            <form:input path="price" type="number" step="0.01" class='form-control'/>
        </div>
        
        <div class="form-group col-sm-12" align='center'>
            <form:label path="dateStart">Date Start</form:label>
            <form:input path="dateStart" type="date" class='form-control'/>
        </div>
    </div>
        
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <input id="CreateViewPrice" type="submit" value="Add View Price" class="submit-button">
    </div>
</form:form>