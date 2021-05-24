
<%--
  Created by IntelliJ IDEA.
  User: Me
  Date: 5/23/2021
  Time: 7:36 PM
  To change this template use File | Settings | File Templates.
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Hotel Nagano</title>
</head>
<body>
<f:form class="form" method="post" modelAttribute="modele" id='logoutForm'>
    <div class="row">
        <div class="form-group col-sm-3">
            <input type="submit" class="form-control btn btn-primary active" value=" Logout " />
        </div>
    </div>
</f:form>

</body>
</html>
