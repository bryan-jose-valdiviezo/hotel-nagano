<%-- 
    Document   : adminRoomsForm
    Created on : May 23, 2021, 9:50:45 AM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>


<script>
    $(document).ready(function (e) {
            $('#CreateRoom').submit(function (e) {
                e.preventDefault();
                var form = $(this).serialize();

                $.ajax({
                    url: 'create-room',
                    type: 'POST',
                    data: form,
                    success: function(response) {
                        $('#adminRoomList').append(response);
                        $('#exampleModal').modal('hide');
                    }
                });
            });
            
            $('#ModifyRoom').submit(function (e) {
                e.preventDefault();
                var roomID = $('#roomID').val();
                
                var form = $(this).serialize();

                $.ajax({
                    url: 'update-room',
                    type: 'POST',
                    data: form,
                    success: function(response) {
                        $('#admin_room_id_'+ roomID).html(response);
                        $('#exampleModal').modal('hide');
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

<form:form id="${formID}" modelAttribute="roomDTO">
    <div class="row my-3">
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='roomID'>ID</form:label>
            <form:input id="roomID" path='roomID' type='number' readonly="true" class='form-control w-75'/>
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='viewID'> View </form:label>
            <form:select path='viewID' class='form-control w-75'>
                <c:forEach items="${views}" var="view">
                    <form:option value="${view.id}">${view.name}</form:option>
                </c:forEach>
            </form:select>

        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='suiteID'> Suite </form:label>
            <form:select path='suiteID' class='form-control w-75'>
                <c:forEach items="${suites}" var="suite">
                    <form:option value="${suite.id}">${suite.name}</form:option>
                </c:forEach>
            </form:select>
        </div>
        
        <div class="form-group col-sm-6" align="center">
            <form:label path='floor'>Floor</form:label>
            <form:input type='number' path='floor' class='form-control w-75'/>
        </div>
        
    </div>
            
    <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <c:if test="${not empty isModify}">
            <input id="CreateViewPrice" type="submit" value="Update Room" class="submit-button">
        </c:if>
            
        <c:if test="${empty isModify}">
            <input id="CreateViewPrice" type="submit" value="Create Room" class="submit-button">
        </c:if>
    </div>
</form:form>
