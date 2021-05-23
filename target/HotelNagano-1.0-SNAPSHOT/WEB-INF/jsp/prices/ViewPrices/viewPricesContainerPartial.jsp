<%-- 
    Document   : viewPricesListPartial
    Created on : May 22, 2021, 6:22:11 PM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
    $(document).ready(function() {
       $("#showViewPriceForm").click(function (e) {
           $.ajax({
               url: "new-view-price",
               type: "GET",
               success: function(response) {
                   $("#modalBody").html(response);
                   $('#exampleModalLabel').html("New View Price");
                   $("#exampleModal").modal("show");
               }
           });
       });
    });
</script>
<div class='container custom_container px-0 my-4 pb-4'>
    <div id="viewPricesList">
        <jsp:include page="viewPricesListPartial.jsp" />
    </div>
    <button id="showViewPriceForm" type="button" class="submit-button">Add View Price</button>
</div>