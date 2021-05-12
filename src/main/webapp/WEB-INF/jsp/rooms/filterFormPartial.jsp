<%-- 
    Document   : filterFormPartial
    Created on : May 9, 2021, 9:59:32 AM
    Author     : Bryan
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<form:form id="FilterForm" modelAttribute="roomSearchDTO" method="post">
    <div class='container custom_container mt-5'>
        <div class='row'>
            <div class='form-group col-sm-6' align='center'>
                <form:label path='dateStart'>Date start</form:label>
                
                <c:if test="${(not empty sessionScope.cart) && (not empty sessionScope.cart.dateStart)}">
                    <form:input type='date' path='dateStart' id="dateStart" readonly="true" class='form-control w-75' />
                </c:if>
                <c:if test="${(empty sessionScope.cart) || (empty sessionScope.cart.dateStart)}">
                    <form:input type='date' path='dateStart' id="dateStart" class='form-control w-75' />
                </c:if>
            </div>

            <div class='form-group col-sm-6' align='center'>
                <form:label path='dateEnd'>Date end</form:label>
                
                <c:if test="${(not empty sessionScope.cart) && (not empty sessionScope.cart.dateEnd)}">
                    <form:input type='date' path='dateEnd' id="dateEnd" readonly="true" class='form-control w-75' />
                </c:if>
                <c:if test="${(empty sessionScope.cart) || (empty sessionScope.cart.dateStart)}">
                    <form:input type='date' path='dateEnd' id="dateEnd" class='form-control w-75' />
                </c:if>
                
            </div>

            <div class='form-group col-sm-6' align='center'>
                <form:label path='viewID'>Room View</form:label>
                <form:select path='viewID' class='form-control w-75'>
                    <form:option value="0">-- ANY--</form:option>
                    <c:forEach items="${views}" var="view">
                        <form:option value="${view.id}">${view.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class='form-group col-sm-6' align='center'>
                <form:label path='suiteID'>Suite</form:label>
                <form:select path='suiteID' class='form-control w-75'>
                    <form:option value="0">-- ANY--</form:option>
                    <c:forEach items="${suites}" var="suite">
                        <form:option value="${suite.id}">${suite.name}</form:option>
                    </c:forEach>
                </form:select>
            </div>

            <div class='form-group col-sm-12' align='center'>
                <form:label path='floor'>Floor</form:label>
                <form:input type='number' path='floor' class='form-control w-50'/>
            </div>

            <div class='form-group col-sm-12'>
                <input type='submit' value='Search' class='submit-button'>
            </div>
        </div>
    </div>
</form:form>