<%-- 
    Document   : mainPage
    Created on : May 9, 2021, 1:48:07 AM
    Author     : Bryan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="resources/style/bootstrap/css/bootstrap.min.css" rel="stylesheet" text="text/css" />
        <link href="resources/style/style.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <jsp:include page="header.jsp"/>
        
        <div class="container">
            <h2>Welcome to</h2>
            <img src="resources/data/hotel-nagano-giant-logo.png" class="mb-2">
        </div>
        
        <div class="container custom_container">
            <h2>Look for a reservation</h2>
            <form>
                <div class="row">
                    <div class="form-group col-lg-6">
                        <label for="email">Email</label>
                        <input type="text" name="email" class="form-control">
                    </div>

                    <div class="form-group col-lg-6">
                        <label for="reservation_number">Reservation Number</label>
                        <input type="number" name="reservation_number" class="form-control">
                    </div>
                </div>
                <div class="form-group">
                    <input type="submit" value="Search" class="submit-button">
                </div>
            </form>
        </div>
    </body>
</html>
