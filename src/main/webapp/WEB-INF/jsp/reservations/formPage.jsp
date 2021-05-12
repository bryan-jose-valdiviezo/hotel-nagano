<%-- 
    Document   : formPage
    Created on : May 11, 2021, 12:32:41 AM
    Author     : Bryan
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/style/bootstrap/css/bootstrap.min.css?version=51" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/style/style.css?version=51" />" rel='stylesheet' type="text/css" />
        <script src="../resources/javascript/my_ajax.js"></script>
        <script src="../resources/javascript/jquery-3.6.0.min.js"></script>
    </head>
    <body>
        <script>
            $(document).ready(function() {
                $('.removeCartLink').click(function (e) {
                   e.preventDefault();
                   var link = $(this);
                   var id = link.attr('data-id');
                   $.ajax({
                       url: "remove-from-cart",
                       type: 'POST',
                       data: 'roomID=' + id,
                       dataType: 'json',
                       success: function(data) {
                           $('#cartCount').html('Cart ('+ data.cartCount +')');
                           $('#cart_room_'+ id).remove();
                           $('#price').attr('value', data.newPrice);
                           
                           if (data.cartCount == 0) {
                               $('#dateStart').val("");
                               $('#dateEnd').val("");
                           }
                       },
                       error: function(){
                           alert("error");
                       }
                   });
                });
            });
        </script>
        
        <jsp:include page="../header.jsp" />
        <div class='container custom_container w-50 mt-5 pt-3'>
            <form:form id="reservationForm" modelAttribute="reservationDTO">
                <div class='row'>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="name">Name</form:label>
                        <form:input path="name" type="text" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="email">Email</form:label>
                        <form:input path="email" type="text" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="customerCount">Customer count</form:label>
                        <form:input path="customerCount" type="number" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="phone">Phone number</form:label>
                        <form:input path="phone" type="number" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="address">Address</form:label>
                        <form:input path="address" type="text" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="price">Total Price</form:label>
                        <form:input id="price" path="price" type="number" step="0.01" readonly="true" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="dateStart">Date start</form:label>
                        <form:input path="dateStart" type="date" readonly="true" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-6' align='center'>
                        <form:label path="dateEnd">Date end</form:label>
                        <form:input path="dateEnd" type="date" readonly="true" class='form-control w-75'/>
                    </div>
                    <div class='form-group col-sm-12' align='center'>
                        <form:label path="specialInstructions">Special instructions</form:label>
                        <form:textarea path="specialInstructions" class='form-control w-50 '/>
                    </div>
                    <div class='form-group col-sm-12'>
                        <input type="submit" value="Place your reservation" class="submit-button">
                    </div>
                </div>
            </form:form>
            <h2>Rooms to check-in</h2>
            <table class='w-100 mb-3'>
                <tr>
                    <th>Rooms</th>
                    <th>View</th>
                    <th>Price</th>
                    <th></th>
                </tr>
                <c:forEach items="${sessionScope.cart.rooms}" var="room">
                    <tr id='cart_room_${room.id}'>
                        <td>${room.suite.name}</td>
                        <td>${room.view.name}</td>
                        <td>${room.roomPrice()}</td>
                        <td><a href="#" data-id="${room.id}" class='removeCartLink'>Remove from reservation</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
