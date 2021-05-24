<%-- 
    Document   : roomsSeachFormPartial
    Created on : May 22, 2021, 11:01:22 PM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>

<script>
    $(document).ready(function(e) {
        $('#RoomSearch').submit(function(e) {
            e.preventDefault();
            var form = $(this).serialize();
            
            $('#adminRoomList').fadeOut('fast', function() {
                $.ajax({
                  url: "search",
                  type: 'GET',
                  data: form,
                  success: function(response) {
                      $('#adminRoomList').html(response);
                      $('#adminRoomList').fadeIn(500);
                  }
               });
            });
        });
        
    });
</script>

<div class="container custom_container my-4">
    <form:form id="RoomSearch" modelAttribute="adminRoomSearchDTO" method="get">
        <div class="row">
            
            <div class="form-group col-sm-6" align="center">
                <form:label path="id">ID</form:label>
                <form:input type="number" path="id" class="form-control w-75" />
            </div>
            
            <div class="form-group col-sm-6" align="center">
                <form:label path="viewID">View</form:label>
                <form:select path="viewID" class="form-control w-75">
                    <form:option value="0">-- ANY--</form:option>
                    <c:forEach items="${views}" var="view">
                        <form:option value="${view.id}">${view.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            
            <div class="form-group col-sm-6" align="center">
                <form:label path="suiteID">Suite</form:label>
                <form:select path="suiteID" class="form-control w-75">
                    <form:option value="0">-- ANY--</form:option>
                    <c:forEach items="${suites}" var="suite">
                        <form:option value="${suite.id}">${suite.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>
            
            <div class='form-group col-sm-6' align='center'>
                <form:label path='floor'>Floor</form:label>
                <form:input type='number' path='floor' class='form-control w-75'/>
            </div>
            
            
            <div class='form-group col-sm-12'>
                <input type='submit' value='Search' class='submit-button'>
            </div>
        </div>
    </form:form>
</div>
