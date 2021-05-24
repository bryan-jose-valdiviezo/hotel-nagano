<%-- 
    Document   : suitePricesListPartial
    Created on : May 22, 2021, 6:22:46 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $(document).ready(function() {
       $("#showSuitePriceForm").click(function (e) {
           $.ajax({
               url: "new-suite-price",
               type: "GET",
               success: function(response) {
                   $("#modalBody").html(response);
                   $('#exampleModalLabel').html("New Suite Price");
                   $("#exampleModal").modal("show");
               }
           });
       });
    });
</script>
<div class='container custom_container px-0 my-4 pb-4'>
    <div id="suitePricesList">
        <jsp:include page="suitePricesListPartial.jsp" />
    </div>
    <button id="showSuitePriceForm" type="button" class="submit-button">Add Suite Price</button>
</div>
