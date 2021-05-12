<%-- 
    Document   : roomListPartial
    Created on : May 9, 2021, 9:59:54 AM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="container my-5">
    <c:forEach items="${rooms}" var="room">
        <div class="container custom_container py-2 h5 selection-scale" style="font-style: oblique;">
            <div class="row">
                <div class="col-sm-3 align-items-center d-flex justify-content-center">
                    ${room.suite.name}
                </div>

                <div class="col-sm-2 align-items-center d-flex justify-content-center">
                    Floor: ${room.floor}
                </div>

                <div class="col-sm-2 align-items-center d-flex justify-content-center">
                    ${room.view.name}
                </div>

                <div class="col-sm-2 align-items-center d-flex justify-content-center">
                    ${room.roomPrice()}
                </div>

                <div class="col-sm-3 align-items-center d-flex justify-content-center">
                    <button type='submit' value="${room.id}" class='book-button'>
                        <c:if test="${(not empty sessionScope.cart) && (sessionScope.cart.isRoomInCart(room.id))}">
                            Cancel
                        </c:if>
                        <c:if test="${empty sessionScope.cart || sessionScope.cart.isRoomInCart(room.id) == false}">
                            Book
                        </c:if>
                    </button>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
