<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="<c:url value="/resources/style/bootstrap/css/bootstrap.min.css?version=51" />" rel="stylesheet" type="text/css" />
        <link href="<c:url value="/resources/style/style.css?version=51" />" rel='stylesheet' type="text/css" />
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div class="container custom_container my-5 py-3">
            <div class="row">
                <div class="col-sm-6 offset-3 ">
                    <h1>Hotel Nagano</h1>
                    <p class='erreur'>${requestScope.message}</p>
                    <f:form class="form" method="post" modelAttribute="modele" id='loginForm'>
                        <div class="row">
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.username}</span>
                                <label>Identifiant</label>
                                <f:input type="text" required="required" class="form-control"
                                       path="username" value='${param.username}' placeholder="Enter your username" />
                            </div>
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.password}</span>
                                <label>Mot de passe</label>
                                <f:password class="form-control" path="password" />
                            </div>
                            <div class="form-group col-sm-12">
                                <input type="submit" class="submit-button" value=" Log In " />
                            </div>
                        </div>
                    </f:form>
                    Vous n'avez pas de compte ? <a href='/HotelNagano/login/createPage'>Inscrivez-vous</a>
                </div>
            </div>
        </div>
    </body>
</html>