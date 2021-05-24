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
      <p class='erreur'>${requestScope.message}</p>
      <f:form class="form" method="post" modelAttribute="modele" id='creationForm'>
      <div class="row">
        <div class="form-group col-sm-12">
          <span class="erreur">${requestScope.name}</span>
          <f:input type="text" required="required" class="form-control"
                   path="name" value='${param.name}' placeholder="Enter your username" />
          <label>Name</label>
        </div>
        <span class="erreur">${requestScope.email}</span>
        <f:input type="email" required="required" class="form-control"
                 path="email" value='${param.email}' placeholder="xyz@abc.com" />
        <label>Email</label>
      </div>
      <div class="form-group col-sm-12">
        <span class="erreur">${requestScope.phone}</span>
        <f:input type="text" required="required" class="form-control"
                 path="phone" value='${param.phone}' placeholder="Your phone number" />
        <label>Phone number</label>
      </div>
      <div class="form-group col-sm-12">
        <span class="erreur">${requestScope.address}</span>
        <f:input type="text" required="required" class="form-control"
                 path="address" value='${param.address}' placeholder="Your address" />
        <label>Address</label>
      </div>
      <div class="form-group col-sm-12">
        <span class="erreur">${requestScope.username}</span>
        <f:input type="text" required="required" class="form-control"
                 path="username" value='${param.username}' placeholder="Enter your username" />
        <label>Username</label>
      </div>
      <div class="form-group col-sm-12">
        <span class="erreur">${requestScope.username}</span>
        <f:input type="password" required="required" class="form-control"
                 path="password" value='${param.username}' placeholder="Choose a password" />
        <label>Password</label>
      </div>
      <div class="form-group col-sm-3">
        <input type="submit" class="form-control btn btn-primary active" value=" OK " />
      </div>
    </div>
    </f:form>
    </div>
  </div>
</body>
</html>
