<%-- 
    Document   : seasonPricesListPartial
    Created on : May 22, 2021, 6:23:31 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $(document).ready(function() {
       $("#showSeasonPriceForm").click(function (e) {
           $.ajax({
               url: "new-season-price",
               type: "GET",
               success: function(response) {
                   $("#modalBody").html(response);
                   $('#exampleModalLabel').html("New Season Price");
                   $("#exampleModal").modal("show");
               }
           });
       });
    });
</script>
<div class='container custom_container px-0 my-4 pb-4'>
    <div id="seasonPricesList">
        <jsp:include page="seasonPricesListPartial.jsp" />
    </div>
    <button id="showSeasonPriceForm" type="button" class="submit-button">Add Season Price</button>
</div>