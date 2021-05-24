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
        <h1 class="mt-5">Register</h1>
        <f:form class="form" method="post" modelAttribute="modele" id='creationForm'>
            <div class="container custom_container">

                <div class="row">

                    <p class='erreur'>${requestScope.message}</p>

                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.name}</span>
                        <label>Name</label>
                        <f:input type="text" required="required" class="form-control" path="name" value='${param.name}' placeholder="Enter your username" />
                    </div>

                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.email}</span>
                        <label>Email</label>
                        <f:input type="email" required="required" class="form-control" path="email" value='${param.email}' placeholder="xyz@abc.com" />
                    </div>


                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.phone}</span>
                        <label>Phone number</label>
                        <f:input type="text" required="required" class="form-control" path="phone" value='${param.phone}' placeholder="Your phone number" />

                    </div>

                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.address}</span>
                        <label>Address</label>
                        <f:input type="text" required="required" class="form-control" path="address" value='${param.address}' placeholder="Your address" />

                    </div>

                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.username}</span>
                        <label>Username</label>
                        <f:input type="text" required="required" class="form-control" path="username" value='${param.username}' placeholder="Enter your username" />

                    </div>

                    <div class="form-group col-sm-6">
                        <span class="erreur">${requestScope.username}</span>
                        <label>Password</label>
                        <f:input type="password" required="required" class="form-control" path="password" value='${param.username}' placeholder="Choose a password" />

                    </div>

                    <div class="form-group col-sm-12">
                        <input type="submit" class="submit-button" value=" Register " />
                    </div>
                </div>
            </div>
        </f:form>
    </body>
</html>
