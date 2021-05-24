<%-- 
    Document   : suitePricesFormPartial
    Created on : May 22, 2021, 7:22:04 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<script>
    $(document).ready(function() {
        $('#SuitePriceForm').submit(function (e) {
            e.preventDefault();
            var form = $(this).serialize();
            
            $.ajax({
                url: "create-suite-price",
                type: "POST",
                data: form,
                success: function(response) {
                    $("#suitePricesList").html(response);
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

<form:form id="SuitePriceForm" modelAttribute="suitePriceDTO" method="post">
    <div class="row my-3">
        
        <div class="form-group col-sm-6">
            <form:label path='suiteID'>Suite</form:label>
            <form:select path='suiteID' class="form-control">
                <c:forEach items="${suites}" var="suite">
                    <form:option value="${suite.id}">${suite.name}</form:option>
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
        <input id="CreateSuitePrice" type="submit" value="Add Suite Price" class="submit-button">
    </div>
</form:form>
