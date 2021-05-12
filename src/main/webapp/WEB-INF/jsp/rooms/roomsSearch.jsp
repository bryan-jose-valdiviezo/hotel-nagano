<%-- 
    Document   : roomsSearch
    Created on : May 9, 2021, 4:51:39 AM
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
            $(document).ready(function(){
               $("#FilterForm").submit(function(e) {
                  e.preventDefault();
                  var form = $(this).serialize();
                  
                  $('#RoomListSection').fadeOut('fast', function() {
                      $.ajax({
                        url: "search",
                        type: 'POST',
                        data: form,
                        success: function(response) {
                            $('#RoomListSection').html(response);
                            $('#RoomListSection').fadeIn(500);
                        }
                     });
                  });
               });
               
               $(document).on('click', 'button', function() {
                   $(this).prop('disabled', true);
                   
                   var buttonClicked = $(this);
                   var dateStart = $('#dateStart').val();
                   var dateEnd = $('#dateEnd').val();
                   var roomID = this.value;
                    $.ajax({
                       url: "book-in",
                       type: 'POST',
                       data: 'dateStart=' + dateStart + '&dateEnd=' + dateEnd + '&roomID=' + roomID,
                       dataType: 'json',
                       success: function(data) {
                           buttonClicked.text(data.buttonText);
                           
                           if (data.cartAmount > 0) {
                               $('#dateStart').prop('readonly', true);
                               $('#dateEnd').prop('readonly', true);
                           }
                           else {
                               $('#dateStart').prop('readonly', false);
                               $('#dateEnd').prop('readonly', false);
                           }
                           
                           $('#cartCount').html('Cart ('+ data.cartAmount +')');
                           buttonClicked.prop('disabled', false);
                       }
                    });
                });
            });
        </script>
        <jsp:include page="../header.jsp" />
        
        <div id="FilterSection">
            <jsp:include page="filterFormPartial.jsp" />
        </div>
        
        <div id="RoomListSection">
            <jsp:include page="roomListPartial.jsp" />
        </div>
    </body>
</html>
