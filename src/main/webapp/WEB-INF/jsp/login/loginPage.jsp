<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="f" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/style/bootstrap/css/bootstrap.min.css" rel="stylesheet" text="text/css" />
        <link href="resources/style/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="../header.jsp"/>
        <div class="container">
            <div class="row">
                <div class="col-sm-6 offset-3 ">
                    <h1>Hotel Nagano</h1>
                    <p class='erreur'>${requestScope.message}</p>
                    <f:form class="form" method="post" modelAttribute="modele" id='loginForm'>
                        <div class="row">
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.username}</span>
                                <f:input type="text" required="required" class="form-control"
                                       path="username" value='${param.username}' placeholder="Enter your username" />
                                <label>Identifiant</label>
                            </div>
                            <div class="form-group col-sm-12">
                                <span class="erreur">${requestScope.password}</span>
                                <f:password class="form-control" path="password" />
                                <label>Mot de passe</label>
                            </div>
                            <div class="form-group col-sm-3">
                                <input type="submit" class="form-control btn btn-primary active" value=" OK " />
                            </div>
                        </div>
                    </f:form>
                    Vous n'avez pas de compte ? <a href='/createPage'>Inscrivez-vous</a>
                </div>
            </div>
        </div>
    </body>
</html>