<%-- 
    Document   : dayPricesListPartial
    Created on : May 22, 2021, 6:23:01 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $(document).ready(function() {
       $("#showDayPriceForm").click(function (e) {
           $.ajax({
               url: "new-day-price",
               type: "GET",
               success: function(response) {
                   $("#modalBody").html(response);
                   $('#exampleModalLabel').html("New Day Price");
                   $("#exampleModal").modal("show");
               }
           });
       });
    });
</script>
<div class='container custom_container px-0 my-4 pb-4'>
    <div id="dayPricesList">
        <jsp:include page="dayPricesListPartial.jsp" />
    </div>
    <button id="showDayPriceForm" type="button" class="submit-button">Add Day Price</button>
</div>